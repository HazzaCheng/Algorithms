package com.hazza.algorithms.tree.LSM;

import com.hazza.algorithms.utils.StringUtils;
import org.junit.Test;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static junit.framework.TestCase.assertEquals;

/**
 * @description:
 * @user: HazzaCheng
 * @contact: hazzacheng@gmail.com
 * @date: 18-8-6
 * Change log:
 */
public class LSMTreeTest {
    private static final String RESOURCES = "src/test/resources/sst/";
    private static final int THREADSHOLD = 10000;
    private LSMTree lsmTree;


    @Test
    public void testGetSstFileNum() {
        lsmTree = new LSMTree(THREADSHOLD, RESOURCES + "t1");

        Class<LSMTree> clazz = LSMTree.class;
        try {
            Method declaredMethod = clazz.getDeclaredMethod("getSstFileNum");
            declaredMethod.setAccessible(true);
            Object res = declaredMethod.invoke(lsmTree);
            System.out.println(res);
            assertEquals(3, res);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testListKeyValues() {
        LSMTree.listAllKeyValueFromFiles(RESOURCES + "t1");
    }

    @Test
    public void testPut() {
        String path = RESOURCES + "t2";
        File file = new File(path);
        if (file.exists()) {
            for (File f: file.listFiles()) {
                f.delete();
            }
            file.delete();
        }
        file.mkdir();

        lsmTree = new LSMTree(THREADSHOLD, path);
        lsmTree.put("Name", "HazzaCheng");
        lsmTree.put("Sex", "Man");
        lsmTree.put("Age", "21");
    }

    @Test
    public void testDelete() {
        String path = RESOURCES + "t2";
        File file = new File(path);
        if (file.exists()) {
            for (File f: file.listFiles()) {
                f.delete();
            }
            file.delete();
        }
        file.mkdir();

        lsmTree = new LSMTree(THREADSHOLD, path);
        lsmTree.put("Name", "HazzaCheng");
        lsmTree.put("Sex", "Man");
        lsmTree.put("Age", "21");

        lsmTree.delete("Name");
    }

    @Test
    public void testUpdate() {
        String path = RESOURCES + "t2";
        File file = new File(path);
        if (file.exists()) {
            for (File f: file.listFiles()) {
                f.delete();
            }
            file.delete();
        }
        file.mkdir();

        lsmTree = new LSMTree(THREADSHOLD, path);
        lsmTree.put("Name", "HazzaCheng");
        lsmTree.put("Sex", "Man");
        lsmTree.put("Age", "21");

        lsmTree.update("Sex", "Woman");
    }

    @Test
    public void testGetFromSkipTable() {
        String path = RESOURCES + "t2";
        File file = new File(path);
        if (file.exists()) {
            for (File f: file.listFiles()) {
                f.delete();
            }
            file.delete();
        }
        file.mkdir();

        lsmTree = new LSMTree(THREADSHOLD, path);
        lsmTree.put("Name", "HazzaCheng");
        lsmTree.put("Sex", "Man");
        lsmTree.put("Age", "21");
        lsmTree.put("Name", "CF");

        String v1 = lsmTree.get("Name");
        String v2 = lsmTree.get("AAA");

        assertEquals("CF", v1);
        assertEquals(null, v2);
    }

    @Test
    public void testGetFromFiles() {
        String path = RESOURCES + "t3";

        lsmTree = new LSMTree(THREADSHOLD, path);

        String v1 = lsmTree.get("Name");
        String v2 = lsmTree.get("AAA");

        assertEquals("CF", v1);
        assertEquals(null, v2);
    }

    @Test
    public void testSolitSstFiles() {
        String path = RESOURCES + "t2";
        File file = new File(path);
        if (file.exists()) {
            for (File f: file.listFiles()) {
                f.delete();
            }
            file.delete();
        }
        file.mkdir();

        lsmTree = new LSMTree(THREADSHOLD, path);
        int cnt = 1000, len =10;

        for (int i = 0; i < cnt; i++) {
            String s1 = StringUtils.getRandomString(10);
            String s2 = StringUtils.getRandomString(10);
            lsmTree.put(s1, s2);
        }

        File files = new File(path);

        int max = 0;
        for (File f : files.listFiles()) {
            if (f.isFile()) {
                int num = Integer.parseInt(f.getName().replace(".sst", ""));
                max = Math.max(num, max);
            }
        }

        assertEquals(5, max);
    }
}
