package com.hazza.algorithms.tree.LSM;

import com.hazza.algorithms.dataStructure.skiplist.SkipList;

import javax.print.attribute.standard.MediaSize;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.zip.CRC32;

/**
 * Created with IntelliJ IDEA.
 * Description: A simple implementation for LSM Tree.
 * User: HazzaCheng
 * Contact: hazzacheng@gmail.com
 * Date: 18-8-4
 * Time: 2:30 PM
 */
public class LSMTree {
    /**
     * Split of records' attributes.
     */
    private final String ATTRIBUTE_SPLIT = ",";
    /**
     * Split of records' location.
     */
    private final String POSITION_SPLIT = ",";
    /**
     * The path of saving .sst files.
     */
    private String path;
    /**
     * The maximum size of .sst file.
     */
    private int threadshold;
    /**
     * The number of existing .sst files.
     */
    private int fileNums;
    /**
     * The skip table.
     */
    private SkipList<KVTuple> table;


    public LSMTree(int threadshold, String path) {
        this.threadshold = threadshold;
        this.path = path;
        fileNums = getSstFileNum();
        table = new SkipList<>(false);
    }

    /**
     * Get the number of sst files under the specified path.
     * @return The number of sst files.
     */
    private int getSstFileNum() {
        File files = new File(path);

        System.out.println(files.getAbsolutePath());

        int max = 0;
        for (File file: files.listFiles()) {
            if (file.isFile()) {
                int num = Integer.parseInt(file.getName().replace(".sst", ""));
                max = Math.max(num, max);
            }
        }

        return max;
    }

    public String get(String key) {

    }

    public void put(String key, String value) {
        int keySize = key.length(), valueSize = 0;

        if (value != null) {
            valueSize = value.length();
        }

        long time = System.currentTimeMillis();
        long crc = getCrc(time, keySize, valueSize, key, value);

        String infos = ioWrite(crc, time, keySize, valueSize, key, value);
        table.insert(new KVTuple(key, infos));
    }

    public void update(String key, String value) {

    }

    public void delete(String key) {

    }

    private long getCrc(String time, int keySize, int valueSize, String key, String value) {
        CRC32 crc32 = new CRC32();
        String str = time + " " + keySize + " " + valueSize + " " + key + " " + value;
        crc32.update(str.getBytes());
        long crc = crc32.getValue();

        return crc;
    }

    private String[] getRecord(String key) {
        KVTuple infos = table.get(new KVTuple(key, null));

        if (infos == null || infos.value == null) {
            return getRecordFromFiles(key);
        }

        String[] values = infos.value.split(POSITION_SPLIT);
        Block block = ioRead(values[0], Integer.parseInt(values[1]), Integer.parseInt(values[2]));

    }

    /**
     * Get record from all sst files.
     * @param key
     * @return
     */
    private String[] getRecordFromFiles(String key) {
        List<Integer> fileNums = new ArrayList<>();
        File files = new File(path);

        for (File file: files.listFiles()) {
            if (file.isFile()) {
                fileNums.add(Integer.parseInt(file.getName().replace(".sst", "")));
            }
        }

        Collections.sort(fileNums);
        int n = fileNums.size();
        for (int i = n - 1; i >= 0; i--) {

        }
    }

    /**
     * Get record from the specified sst file.
     * @param key
     * @param fileName
     * @return
     */
    private String[] getRecordFromFile(String key, String fileName) {

    }

    /**
     * Write into sst file.
     * @param crc
     * @param time
     * @param keySize
     * @param valueSize
     * @param key
     * @param value
     * @return The location of the key.
     */
    private String ioWrite(long crc, long time, int keySize, int valueSize, String key, String value) {
        String curFile = path + "/" + fileNums + ".sst";
        File file = new File(curFile);

        if (file.length() >= threadshold) {
            ++fileNums;
            curFile = path + "/" + fileNums + ".sst";
            file = new File(curFile);
        }

        Block block = new Block(crc, time, keySize, valueSize, key, value);
        byte[] bytes = block.getBytes(ATTRIBUTE_SPLIT);
        int len = bytes.length;
        int start = 0;

        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            start = (int) raf.getFilePointer();
            raf.write(bytes, start, len);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return curFile + POSITION_SPLIT + start + POSITION_SPLIT + len;
    }

    private Block ioRead(String fileName, int start, int length) {

    }
}
