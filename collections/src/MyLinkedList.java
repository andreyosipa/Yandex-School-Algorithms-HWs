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
public class MyLinkedList implements MyList {

    private Node head,tail;
    private int listCount;
    
    MyLinkedList(){
        head = null;
        tail = null;
        listCount = 0;
    }
    
    @Override
    public void add(Object e) {
            Node elem = new Node(e , null , this.tail);
            if (this.tail != null) this.tail.next = elem;
            this.tail = elem;
            this.listCount++;
            if (listCount == 1) this.head = elem;
    }

    @Override
    public void add(int index, Object e) {
        if (index == listCount) {
            addLast(e);
        }
        else if (index == 1){
            addFirst(e);
        }
        else {
            Node tmp = getNode(index);
            Node newElement = new Node( e , tmp , tmp.prev);
            tmp.prev.next = newElement;
            tmp.prev = newElement;
            this.listCount++;
        }
    }

    @Override
    public void addAll(Object[] c) {
        for (Object i : c ){
            this.add(i);
        }
    }

    @Override
    public void addAll(int index, Object[] c) {
        for (int i=0 ; i < c.length ; i++){
            this.add(index+i , c[i]);
        }
    }

    @Override
    public Object get(int index) {
        Node ans = getNode(index);
        return ans.getElement();
    }

    @Override
    public Object remove(int index) {
        Node tmp = new Node();
        if (index == listCount) {
            tmp.setElement(removeLast());
        }
        else if (index == 1) {
            tmp.setElement(removeFirst());
        }
        else {
            tmp = getNode(index);
            tmp.prev.next = tmp.next;
            tmp.next.prev = tmp.prev;
            listCount--;
        }
        return tmp.getElement();
    }

    @Override
    public void set(int index, Object e) {
        Node tmp = getNode(index);
        tmp.setElement(e);
    }

    @Override
    public int indexOf(Object o) {
        int index = 0;
        Node Elem = this.head;
        while ((index < listCount) && (Elem.getElement() != o)){
            index++;
        }
        if (index == listCount) index = -1;
        return index;
    }

    @Override
    public int size() {
        return listCount;
    }

    @Override
    public void clear() {
        this.head = null;
        this.tail = null;
        this.listCount = 0;
    }

    @Override
    public boolean isEmpty() {
        return (tail == null);
    }

    @Override
    public Object[] toArray() {
        Object[] ans = new Object[listCount];
        Node elem = this.head;
        int index = 0;
        while (elem != null){
            ans[index] = elem.getElement();
            elem = elem.next;
            index++;
        }
        return ans;
    }
    
    @Override
    public String toString() {
        String s = "";
        Node elem = this.head;
        while (elem != null){
            s = s + elem.toString() + " ";
            elem = elem.next;
        }
        return s;
    }
    
    public void addFirst(Object e){ //- добавляет элемент в начало
        Node newElem = new Node( e , this.head , null);
        if (this.head != null) this.head.prev = newElem;
        this.head = newElem;
        this.listCount++;
        if (listCount == 1) this.tail = newElem;
    }
    
    public void addLast(Object e){ //- добавляет элемент в конец
        Node newElem = new Node( e , null , this.tail);
        if (this.tail != null) this.tail.next = newElem;
        this.tail = newElem;
        this.listCount++;
        if (listCount == 1) this.head = newElem;
    }
    
    public Object getFirst(){
        if (this.head != null) return this.head.getElement();
        else return null;
    }
    
    public Object getLast(){
        if (this.tail != null) return this.tail.getElement();
        else return null;
    }
    
    public Object removeFirst(){// - все методы remove возвращают объект который удаляется
        Node ans = this.head;
        this.head = this.head.next;
        this.head.prev = null;
        if (!this.isEmpty()) this.listCount--;
        return ans.getElement();
    }
    
    public Object removeLast(){
        Node ans = this.tail;
        this.tail = this.tail.prev;
        this.tail.next = null;
        if (!this.isEmpty()) this.listCount--;
        return ans.getElement();
    }
    
    private Node getNode(int index){
        Node ans = new Node(null,null,null);
        if ((index > this.listCount) || (index < 0)){
            try {
                throw new IndexOutOfBoundsExeption("Index is out of bounds.");
            } catch (IndexOutOfBoundsExeption ex) {
                Logger.getLogger(MyLinkedList.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if (index < (listCount / 2 + 1) ) {
            ans = head;
            for(int i=1 ; i<index ; i++)
            {
                ans = ans.next;
            }
        }
        else if (index > (listCount / 2 - 1) ) {
            ans = tail;
            for(int i=listCount ; i>index ; i--)
            {
                ans = ans.prev;
            }
        }
        return ans;
    }
    
}
