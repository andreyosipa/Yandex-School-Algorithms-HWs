package com.mycompany.cake;

import java.io.PrintWriter;
import java.util.Scanner;
import static java.lang.Double.max;
import static java.lang.Double.min;
import java.lang.Math;
import static java.lang.System.out;

public class Main2
{
   private static double cut(double w, double l, int p){
       double ratio_max = 1000000;
       double ratio_A;
       double ratio_B;
       if (p>1) {
        short i;
        for (i = 1; i<=p/2 ; i++){
            ratio_A = cut(i*w/p, l, i);
            ratio_B = cut((p-i)*w/p, l, p-i);
            ratio_max = min (ratio_max , max(ratio_A, ratio_B));
        }
        for (i = 1; i<=p/2 ; i++){
            ratio_A = cut(w, i*l/p, i);
            ratio_B = cut(w, (p-i)*l/p, p-i);
            ratio_max = min (ratio_max , max(ratio_A, ratio_B));
        }
      }
      else { ratio_max = max(l/w,w/l); }
      return ratio_max;
   }
   public static void main(String[] args)   {
      Scanner in = new Scanner(System.in);
      PrintWriter out = new PrintWriter(System.out);
      
      short width;
      short length;
      short pieces;
      double ratio=0;
      length = in.nextShort();
      width = in.nextShort();
      pieces = in.nextShort(); 
      ratio = cut(width,length,pieces);
      out.printf("%.4f",ratio);
      out.flush();
   }
}