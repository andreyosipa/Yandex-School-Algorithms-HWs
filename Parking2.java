package com.mycompany.a;

import java.util.*;

public class Main {

    static class MyQueue{
        
        static class Element{
            static public  Element next;
            static public Element prev;
            static public int value;

            private Element(Element Next, Element Prev, int Val) {
                this.next = Next;
                this.prev = Prev;
                this.value = Val;
            }
        }
        
        static public Element Begin;
        static public Element End;
        
        MyQueue (Element B, Element E)
        {
            this.Begin = B;
            this.End = E;
        }
        
        public static void pushEnd(int b, MyQueue A){
            Element elem = new Element(null,A.End,b);
            MyQueue B = new MyQueue(A.Begin, elem);
            A = B;
        }
        
        public static int getEnd(MyQueue A)
        {
            Element elem;
            elem = A.End;
            MyQueue B = new MyQueue(A.Begin,elem.prev);
            B.End.next = null;
            A = B;
            return elem.value;
        }
        
        public static int getBegin(MyQueue A){
            Element elem;
            elem = A.Begin;
            MyQueue B = new MyQueue(elem.next,A.End);
            B.Begin.prev = null;
            A = B;
            return elem.value;
        }
        
        public static boolean empty(MyQueue A)
        {
            return (A.Begin == null);
        }
        
        public static void init(MyQueue A){
            A.Begin = null;
            A.End = null;
        }
    }
    
  public void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      int n, m, price[], weight[], result;
      n = sc.nextInt();
      m = sc.nextInt();
      price = new int[n+2];
      
      result = 0;
      for (int i = 0; i < n; i++) {
          price[i] = sc.nextInt();
      }
      weight = new int[m+2];
      for (int k = 0; k < m; k++) {
          weight[k] = sc.nextInt();
      }
      TreeSet<Integer> freeplace = new TreeSet<Integer>();
      for (int i = 0; i < n; i++) {
          freeplace.add(i+1);
      }
      Map<Integer, Integer> treeMap = new TreeMap<Integer,Integer>();
      MyQueue queue = new MyQueue(null,null);
      MyQueue.init(queue);
      for (int i = 0; i < 2 * m; i++) {
          int b = sc.nextInt();
          if (b > 0) {
              if (freeplace.isEmpty()) {
                  MyQueue.pushEnd(b, queue);
              } else {
                  int c = freeplace.pollFirst();
                  treeMap.put(b, c);
                  result = result + weight[b-1] * price[c-1];
              }

          } else {
             int d =  treeMap.remove(-b);
              if (MyQueue.empty(queue)){
                  freeplace.add(d);
                  
             }
              else {
                  
                  int c = MyQueue.getBegin(queue);
                  treeMap.put(c,d);
                  result = result+price[d-1]*weight[c-1];
              }
          }


      }
      System.out.println(result);
  }
}