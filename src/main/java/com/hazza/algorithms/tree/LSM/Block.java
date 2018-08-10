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
    long crc;
    long time;
    int keySize;
    int valueSize;
    String key;
    String value;

    public Block(long crc, long time, int keySize, int valueSize, String key, String value) {
        this.crc = crc;
        this.time = time;
        this.keySize = keySize;
        this.valueSize = valueSize;
        this.key = key;
        this.value = value;
    }

    public byte[] getBytes(String attributeSplit) {
        StringBuffer sb = new StringBuffer();
        sb.append(crc)
                .append(attributeSplit).append(time)
                .append(attributeSplit).append(keySize)
                .append(attributeSplit).append(valueSize)
                .append(attributeSplit).append(key)
                .append(attributeSplit).append(value);

        return sb.toString().getBytes();
    }

    public static Block rcoverFromBytes(byte[] bytes, String attributeSplit) {
        String str = new String(bytes);
        String[] values = str.split(attributeSplit);

        return new Block(Long.parseLong(values[0]), Long.parseLong(values[1]), Integer.parseInt(values[2]), Integer.parseInt(values[3]), values[4], values[5]);
    }
}
