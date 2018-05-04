package com.hazza.algorithms.SnowFlake;

import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 * Description: Implement SnowFlake, Snowflake is a service used to generate unique IDs for objects within Twitter.
 *
 * @author Hazzacheng
 * Contact: hazzacheng@gmail.com
 * Date: 18-5-4
 * Time: 下午8:11
 */
public class SnowFlakeIdGenerator {

    /**
     * Initial timestamp (2017-01-01)
     */
    private static final long INITIAL_TIME_STAMP = 1483200000000L;

    /**
     * The number of bits occupied by the worker machine id.
     */
    private static final long WORKER_ID_BITS = 5L;

    /**
     * The number of bits occupied by the data center id.
     */
    private static final long DATACENTER_ID_BITS = 5L;

    /**
     * The maximum id of worker machine.
     */
    private static final long MAX_WORKER_ID = ~(-1L << WORKER_ID_BITS);

    /**
     * The maximum id of data center id.
     */
    private static final long MAX_DATACENTER_ID = ~(-1L << DATACENTER_ID_BITS);

    /**
     * The number of bits occupied by the sequence id.
     */
    private final long SEQUENCE_BITS = 12L;

    /**
     * The offset of the worker machine id.
     */
    private final long WORKERID_OFFSET = SEQUENCE_BITS;

    /**
     * The offset of the data center id.
     */
    private final long DATACENTERID_OFFSET = SEQUENCE_BITS + WORKER_ID_BITS;

    /**
     * The offset of the timestamp.
     */
    private final long TIMESTAMP_OFFSET = SEQUENCE_BITS + WORKER_ID_BITS + DATACENTER_ID_BITS;

    /**
     * The mask of sequence id.
     */
    private final long SEQUENCE_MASK = ~(-1L << SEQUENCE_BITS);

    /**
     * The id of worker machine.
     */
    private long workerId;

    /**
     * The id of data center id.
     */
    private long datacenterId;

    /**
     * The id of sequence id.
     */
    private long sequence = 0L;

    /**
     * Last timestamp of generating id.
     */
    private long lastTimestamp = -1L;

    /**
     * Constructor.
     * @param workerId Worker machine id (0-31).
     * @param datacenterId Data center id (0-31)
     */
    public SnowFlakeIdGenerator(long workerId, long datacenterId) {
        if (workerId > MAX_WORKER_ID || workerId < 0) {
            throw new IllegalArgumentException(
                    String.format("Worker ID cannot be greater than %d or less than 0.", MAX_WORKER_ID));
        }
        if (datacenterId > MAX_DATACENTER_ID || datacenterId < 0) {
            throw new IllegalArgumentException(
                    String.format("Data Center ID cannot be greater than %d or less than 0.", MAX_DATACENTER_ID));
        }

        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    /**
     * Get the next Id.
     * @return The new Id.
     */
    public synchronized long nextId() {
        long timestamp = System.currentTimeMillis();

        if (timestamp < lastTimestamp) {
            throw new RuntimeException("Current timestamp is less than the last timestamp!");
        }
        if (timestamp == lastTimestamp) {
            sequence = (sequence + 1) & SEQUENCE_MASK;
            // Sequence id equal to 0 indicates that the sequence has grown to a maximum value within a millisecond.
            if (sequence == 0) {
                // Block to the next millisecond and get a new timestamp.
                timestamp = tillNextMills(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = timestamp;

        return ((timestamp - INITIAL_TIME_STAMP) << TIMESTAMP_OFFSET) |
                (datacenterId << DATACENTERID_OFFSET) |
                (workerId << WORKERID_OFFSET) |
                sequence;
    }

    /**
     * Block to the next millisecond and get a new timestamp.
     * @param lastTimestamp The last timestamp.
     * @return Current timestamp.
     */
    protected long tillNextMills(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();

        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }

        return timestamp;
    }

    public static void main(String[] args) {
        final SnowFlakeIdGenerator idGenerator = new SnowFlakeIdGenerator(1, 1);
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 1000; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    long id = idGenerator.nextId();
                    System.out.println(id);
                }
            });
        }

        executorService.shutdown();
    }

}
