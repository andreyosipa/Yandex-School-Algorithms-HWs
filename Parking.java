/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2a.pkg1;

import java.util.*;

public class Main {

    static class MyQueue{
        
        static class Element{
            public  Element next;
            public Element prev;
            public int value;

            private Element(Element Next, Element Prev, int Val) {
                this.next = Next;
                this.prev = Prev;
                this.value = Val;
            }
        }
        
        public Element Begin;
        public Element End;
        
        MyQueue (Element B, Element E)
        {
            this.Begin = B;
            this.End = E;
        }
        
        public static void pushEnd(int b, MyQueue A){
            Element elem = new Element(null,A.End,b);
            if (A.Begin != null) 
            {
                A.End.next = elem;
                A.End = elem;
                A.End.next = null;
            }
            else 
            {
                A.Begin = elem;
                A.End = elem;
            };
        }
        
        public static int getEnd(MyQueue A)
        {
            Element elem = new Element(A.End.next , A.End.prev , A.End.value);
            if (elem.prev != null) 
            { 
                A.End  = new Element (elem.prev.next , elem.prev.prev , elem.prev.value );
                A.End = null;
            }
            else 
            { 
                A.Begin = null;
                A.End = null;
            };
            return elem.value;
        }
        
        public static int getBegin(MyQueue A) throws CloneNotSupportedException{
            Element elem = new Element(A.Begin.next , A.Begin.prev , A.Begin.value );
            A.Begin = elem.next;
            return elem.value;
        }
        
        public static boolean empty(MyQueue A)
        {
            return (A.Begin == null);
        }
        
        public static void init(MyQueue X){
            X.Begin = null;
            X.End = null;
        }
    }
    
  public static void main(String[] args) throws CloneNotSupportedException {
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
