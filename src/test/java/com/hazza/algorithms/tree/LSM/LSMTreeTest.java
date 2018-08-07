package com.hazza.algorithms.tree.LSM;

import org.junit.Before;
import org.junit.Test;

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
    private static final String RESOURCES = "src/test/resources/sst";
    private static final int THREADSHOLD = 10000;
    private LSMTree lsmTree;


    @Test
    public void testGetSstFileNum() {
        lsmTree = new LSMTree(THREADSHOLD, RESOURCES);

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
}
