package com.hazza.algorithms.tree.LSM;

import java.io.Serializable;

/**
 * @description: The block for detailed data.
 * @user: HazzaCheng
 * @contact: hazzacheng@gmail.com
 * @date: 18-8-8
 * Change log:
 */
public class Block implements Serializable {
    public final static char BLOCK_SPLIT = '\n';
    public final static int BLOCK_SPLIT_BYTE_SIZE = 1;

    long crc;
    long time;
    int keySize;
    int valueSize;
    String key;
    String value;

    public Block() {}

    public Block(long crc, long time, int keySize, int valueSize, String key, String value) {
        this.crc = crc;
        this.time = time;
        this.keySize = keySize;
        this.valueSize = valueSize;
        this.key = key;
        this.value = value;
    }

    /**
     * Convert Block object into bytes.
     * @param attributeSplit The split of each attributes.
     * @return Byte.
     */
    public byte[] getBytes(String attributeSplit) {
        StringBuffer sb = new StringBuffer();
        sb.append(crc)
                .append(attributeSplit).append(time)
                .append(attributeSplit).append(keySize)
                .append(attributeSplit).append(valueSize)
                .append(attributeSplit).append(key)
                .append(attributeSplit).append(value)
                .append(BLOCK_SPLIT);

        return sb.toString().getBytes();
    }

    /**
     * Recover block object from bytes.
     * @param bytes
     * @param attributeSplit
     * @return
     */
    public static Block rcoverFromBytes(byte[] bytes, String attributeSplit) {
        String str = new String(bytes);
        String[] values = str.split(attributeSplit);

        return new Block(Long.parseLong(values[0].trim()), Long.parseLong(values[1].trim()), Integer.parseInt(values[2].trim()), Integer.parseInt(values[3].trim()), values[4].trim(), values[5].trim());
    }

    @Override
    public String toString() {
        return "[" + key + ", " + value + "]";
    }
}
