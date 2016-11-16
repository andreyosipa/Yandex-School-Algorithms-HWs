/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.yandex.collections;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andrey
 */
public class MyArrayList implements MyList {
    
    public Object[] data;
    public int size;
    
    @Override
    public void add(Object e) {
        ensureCapacity(this.size + 1);
        this.data[this.size] = e;
        size++;
    }

    @Override
    public void add(int index, Object e) {
        if ( checkIndex(index)) {
            ensureCapacity(this.size + 1);
            System.arraycopy(this.data, index, this.data, index + 1, size - index);
            this.data[index] = e;
            size++;
        }
    }

    @Override
    public void addAll(Object[] c) {
        for (Object i : c){
            add(i);
        }
    }

    @Override
    public void addAll(int index, Object[] c) {
        if ( checkIndex(index)) {
            for (int i = 0 ; i < c.length ; i++){
                add(index + i , c[i]);
            }
        }
    }

    @Override
    public Object get(int index) {
        if ( checkIndex(index)) {
            return this.data[index];
        }
        else return null;
    }

    @Override
    public Object remove(int index) { //MUST CHANGE SIZE
        if ( checkIndex(index)) {
            Object e = this.data[index];
            System.arraycopy(this.data, index+1, this.data, index, this.size - index - 1);
            return e;
        }
        else return null;
    }

    @Override
    public void set(int index, Object e) {
        if ( checkIndex(index)) {
            this.data[index] = e;
        }
    }

    @Override
    public int indexOf(Object o) {
        int i = 0;
        while ((i < size) && (this.data[i] != o)) {
            i++;
        }
        if ( i == size) {
            return -1;
        }
        else return i;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void clear() {
        for (int i = 0 ; i<size ; i++){
            this.data[i] = null;
        }
        this.size = 0;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Object[] toArray() {
        Object[] dataArray = new Object[size];
        System.arraycopy(this.data, 0, dataArray, 0, size);
        return dataArray;
    }
    
    @Override
    public String toString(){
        String s = "";
        for (int i = 0; i<size ; i++){
            s = s + this.data[i].toString() + " ";
        }
        return s;
    }
    
    MyArrayList() {
        this(10);
    }     
    
    MyArrayList(int initialCapacity) {
        this.data = new Object[initialCapacity];
        this.size = 0;
    }    
    
    public void ensureCapacity(int minCapacity) {
        int i = 0;
        while ((this.data[i] != null) && ( i < this.data.length)){
            i++;
        }
        if (this.size - i < minCapacity) {
            Object[] oldData = this.data;
            this.data = new Object[i + minCapacity];
            System.arraycopy(oldData, 0, this.data, 0, this.size);
        }
    }
    
    private boolean checkIndex(int index)
    {
        if ((index < 0) || (index >= size) ){
            try {
                throw new IndexOutOfBoundsExeption("Index is out of bounds.");
            } catch (IndexOutOfBoundsExeption ex) {
                Logger.getLogger(MyLinkedList.class.getName()).log(Level.SEVERE, null, ex);
            }
            return false;
        }
        else {
            return true;
        }
    }
    
}
