package com.hazza.algorithms.Greedy.huffmanCode;

/**
 * Created with IntelliJ IDEA.
 * Description: A class for a character with its frequency.
 * Author: HazzaCheng
 * Contact: hazzacheng@gmail.com
 * Date: 2017-08-12
 * Time: 9:54 PM
 */
class FrequenceChar {
    char c;
    int frequency;

    FrequenceChar(char c, int frequency) {
        this.c = c;
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return "(" + c + ", " + frequency + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FrequenceChar that = (FrequenceChar) o;

        if (c != that.c) return false;
        return frequency == that.frequency;
    }

    @Override
    public int hashCode() {
        int result = (int) c;
        result = 31 * result + frequency;
        return result;
    }
}
