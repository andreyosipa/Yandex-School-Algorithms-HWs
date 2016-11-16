/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.yandex.collections;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Andrey
 */
public class MyArrayListTest {
    
    public MyArrayListTest() {
    }
    
    @Test
    public void testAdd_Object() {
        System.out.println("add");
        Object e = 777;
        MyArrayList instance = new MyArrayList();
        instance.add(e);
        Object[] expResult = {777};
        Object[] result = instance.toArray();
        assertArrayEquals(expResult , result);
    }

    @Test
    public void testAdd_int_Object() {
        System.out.println("add");
        int index = 2;
        Object e = 14;
        MyArrayList instance = new MyArrayList();
        instance.add(12);
        instance.add(16);
        instance.add(18);
        instance.add(index, e);
        Object[] expResult = {12 , 14 , 16 , 18};
        Object[] result = instance.toArray();
        assertArrayEquals(expResult , result);
    }

    @Test
    public void testAddAll_ObjectArr() {
        System.out.println("addAll");
        Object[] c = {"abc" , 13 , 17};
        MyArrayList instance = new MyArrayList();
        instance.add(12);
        instance.add(16);
        instance.add(18);
        instance.addAll(c);
        Object[] expResult = {12 , 16 , 18 , "abc" , 13 , 17};
        Object[] result = instance.toArray();
        assertArrayEquals(expResult , result);
    }

    @Test
    public void testAddAll_int_ObjectArr() {
        System.out.println("addAll");
        int index = 2;
        Object[] c = {"abc" , 13 , 17};
        MyArrayList instance = new MyArrayList();
        instance.add(12);
        instance.add(16);
        instance.add(18);
        instance.addAll(index, c);
        Object[] result = instance.toArray();
        Object[] expResult = {12 , "abc" , 13 , 17 , 16 , 18};
        assertArrayEquals(expResult , result);
    }

    /**
     * Test of get method, of class MyArrayList.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        int index = 2;
        MyArrayList instance = new MyArrayList();
        instance.add(12);
        instance.add(16);
        instance.add(18);
        Object expResult = 16;
        Object result = instance.get(index);
        assertEquals(expResult, result);
    }

    /**
     * Test of remove method, of class MyArrayList.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        int index = 2;
        MyArrayList instance = new MyArrayList();
        instance.add(12);
        instance.add(16);
        instance.add(18);
        Object expResult = 16;
        Object result = instance.remove(index);
        assertEquals(expResult, result);
    }

    /**
     * Test of set method, of class MyArrayList.
     */
    @Test
    public void testSet() {
        System.out.println("set");
        int index = 0;
        Object e = 35678;
        MyArrayList instance = new MyArrayList();
        instance.add(12);
        instance.add(16);
        instance.add(18);
        instance.set(index, e);
        assertEquals(instance.get(2) , e);
    }

    /**
     * Test of indexOf method, of class MyArrayList.
     */
    @Test
    public void testIndexOf() {
        System.out.println("indexOf");
        Object o = null;
        MyArrayList instance = new MyArrayList();
        int expResult = 0;
        int result = instance.indexOf(o);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of size method, of class MyArrayList.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        MyArrayList instance = new MyArrayList();
        int expResult = 0;
        int result = instance.size();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clear method, of class MyArrayList.
     */
    @Test
    public void testClear() {
        System.out.println("clear");
        MyArrayList instance = new MyArrayList();
        instance.clear();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isEmpty method, of class MyArrayList.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        MyArrayList instance = new MyArrayList();
        boolean expResult = false;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toArray method, of class MyArrayList.
     */
    @Test
    public void testToArray() {
        System.out.println("toArray");
        MyArrayList instance = new MyArrayList();
        Object[] expResult = null;
        Object[] result = instance.toArray();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class MyArrayList.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        MyArrayList instance = new MyArrayList();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ensureCapacity method, of class MyArrayList.
     */
    @Test
    public void testEnsureCapacity() {
        System.out.println("ensureCapacity");
        int minCapacity = 0;
        MyArrayList instance = new MyArrayList();
        instance.ensureCapacity(minCapacity);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
