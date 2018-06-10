package com.hazza.algorithms.string.KMP;

/**
 * Created with IntelliJ IDEA.
 * Description: Search for occurrences of a "word" W within a main "text string" S by KMP Algorithms.
 * @author Hazzacheng
 * Contact: hazzacheng@gmail.com
 * Date: 18-3-28
 * Time: 9:10 PM
 */
public class StringMatch {

    /**
     * Util class, cannot be instantiated.
     */
    private StringMatch() {}

    /**
     * Get the next array.
     * @param s The target array.
     * @return The next array.
     */
    private static int[] getNext(String s) {
        int len = s.length();
        int[] next = new int[len];

        // 初始化为-1，-1表示不存在相同的最大前缀和最大后缀
        next[0] = -1;
        // j初始化为-1
        for (int i = 1, j = -1; i < len; i++) {
            // 如果下一个不相同，那么j就变成next[j]，注意next[j]是小于j的，无论j取任何值。
            while (j > -1 && s.charAt(i) != s.charAt(j + 1)) {
                j = next[j];
            }

            if (s.charAt(i) == s.charAt(j + 1)) {
                ++j;
            }

            next[i] = j;
        }

        return next;
    }

    /**
     * Use KMP algorithm to match target string with the string whether including target string.
     * @param str The string whether including target string.
     * @param target The target string.
     * @return True or false.
     */
    public static boolean kmp(String str, String target) {
        boolean flag = false;
        int[] next = getNext(target);

        for (int i = 0, j = -1; i < str.length(); i++) {
            while (j > -1 && str.charAt(i) != target.charAt(j + 1)) {
                // 如果不等，继续将长度为i的字符串分割，获得其最大公共长度next[j]
                j = next[j];
            }
            if (str.charAt(i) == target.charAt(j + 1)) {
                ++j;
            }
            if (j == target.length() - 1) {
                flag = true;
                System.out.println("find at index " + (i - j));
                System.out.println(str.subSequence(i - j, i + 1));
                // 得到一个匹配之后都要对j重新赋值
                j = next[j];
            }
        }

        return flag;
    }

}
