/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.yandex.collections;

/**
 *
 * @author Andrey
 */
public class Node {
    private Object element;
    Node next,prev;
    
    public Node(){
        this(null,null,null);
    }
    
    public Node(Object E , Node n , Node p){
        this.element = E;
        this.next = n;
        this.prev = p;
    }
    
    public Object getElement(){
        return this.element;
    }
    
    public void setElement(Object E){
        this.element = E;
    }
    
    public void setNext(Node n){
        this.next = n;
    }
    
    public void setPrev(Node p){
        this.prev = p;
    }
}
