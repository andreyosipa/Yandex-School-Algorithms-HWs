package com.mycompany;
import java.util.*;

public class Main {
    static double A[][] = new double [3][2];
    static double B[][] = new double [3][2];
    static double Poly[][] = new double[6][2];
    static int NumberOfVertex;

    static double max(double a, double b){
        if (a>b) {
            return a;
        }
        else return b;
    }

    static double min(double a, double b){
        if (a>b) {
            return b;
        }
        else return a;
    }

    static boolean RectanglesIntersects(double x1,double y1,double x2,double y2,
                             double x3,double y3,double x4,double y4)
    {
        if ((x3 - x2)*(x4 - x1) > 0) return false;
        if ((y3 - y2)*(y4 - y1) > 0) return false;
        return true;
    }

    static boolean intersect(double x1,double y1, double x2, double y2,
                     double x3,double y3, double x4, double y4)
    {
        double ABx, ABy, ACx, ACy, ADx, ADy;
        double CAx, CAy, CBx, CBy, CDx, CDy;
        double ACxAB, ADxAB, CAxCD, CBxCD;
        if (!RectanglesIntersects(min(x1,x2),min(y1,y2),max(x1,x2),max(y1,y2),
                min(x3,x4),min(y3,y4),max(x3,x4),max(y3,y4))) return false;
        ACx = x3 - x1; ACy = y3 - y1;
        ABx = x2 - x1; ABy = y2 - y1;
        ADx = x4 - x1; ADy = y4 - y1;

        CAx = x1 - x3; CAy = y1 - y3;
        CBx = x2 - x3; CBy = y2 - y3;
        CDx = x4 - x3; CDy = y4 - y3;

        ACxAB = ACx * ABy - ACy * ABx;
        ADxAB = ADx * ABy - ADy * ABx;
        CAxCD = CAx * CDy - CAy * CDx;
        CBxCD = CBx * CDy - CBy * CDx;

        return ACxAB * ADxAB <= 0 && CAxCD * CBxCD <= 0;
    }


    static double[] intersection(double[] A , double[] B , double[] C , double[] D){
           double[] Intersection = new double[3];
        if (intersect(A[0],A[1],B[0],B[1],C[0],C[1],D[0],D[1])) {
            Intersection[0] = 1;
            double det;
            det = (B[0]-A[0])*(C[1]-D[1])-(B[1]-A[1])*(C[0]-D[0]);
            Intersection[1] = ( (C[0]-A[0])*(C[1]-D[1])-(C[1]-A[1])*(C[0]-D[0]) ) / det;
            Intersection[2] = ( (B[0]-A[0])*(C[1]-A[1])-(B[1]-A[1])*(C[0]-A[0]) ) / det;
            Intersection[1] = A[0] + (B[0]-A[0]) * Intersection[1];
            Intersection[2] = A[1] + (B[1]-A[1]) * Intersection[1];
        }
        else {
            Intersection[0] = 0;
        }
        return Intersection;
    }

    static void convexOfPoints(int num){
        int k;
        double tmp;
        double sign;
        boolean f;
        k = 0;
        int i = 1;
        while(i<num)
        {
            sign = 0;
            f = true;
            for (int j = k+1; j<num ; j++){
                if (j!=i){
                    tmp = (Poly[i][1]-Poly[k][1])*(Poly[j][0]-Poly[k][0]) - (Poly[i][0]-Poly[k][0])*(Poly[j][1]-Poly[k][1]);
                    if (sign*tmp < 0) {
                        f = false;
                    }
                    sign = tmp;
                }
            }
            if (f){
                double[] t = new double[2];
                t[0] = Poly[i][0];
                t[1] = Poly[i][1];
                Poly[i][0] = Poly[k+1][0];
                Poly[i][1] = Poly[k+1][1];
                Poly[k+1][0] = t[0];
                Poly[k+1][1] = t[1];
                k++;
                i = k+1;
            }
            else{
                i++;
            }
        }
    }

    static boolean isVertexInside(double[] A, double[][] B){
        int k;
        double tmp,sign;
        sign = 0;
        boolean f;
        f = true;
        for (int i = 0 ; i<3 ; i++ ){
            k = i+1;
            k = k%3;
            tmp = (B[i][0]-A[0])*(B[k][1]-B[i][1]) - (B[i][1]-A[1])*(B[k][0]-B[i][0]);
            if (sign*tmp<0) { f = false; }
            sign = tmp;
        }
        if (f) return true;
        else return false;
    }

    static double polySquare(int num){
        double s = 0;
        for(int i = 0 ; i < num-1 ; i++ ){
            s = s + (Poly[i+1][1]+Poly[i][1])*(Poly[i+1][0]-Poly[i][0])*0.5;
        }
        s = s + (Poly[1][1]+Poly[num][1])*(Poly[1][0]-Poly[num][0])*0.5;
        s = Math.abs(s);
        return s;
    }

    public static void main(String[] args){
        NumberOfVertex = 0;
        Scanner sc = new Scanner(System.in);
        for (int i=0 ; i<3 ; i++)
        {
            A[i][0] = sc.nextInt();
            A[i][1] = sc.nextInt();
        }
        for (int i=0 ; i<3 ; i++)
        {
            B[i][0] = sc.nextInt();
            B[i][1] = sc.nextInt();
        }
        for (int i=0 ; i<6 ; i++)
        {
            Poly[i][0] = -101;
            Poly[i][1] = -101;
        }
        double[] tmp = new double[3];
        int k;
        int l;
        for (int i=0 ; i<3 ; i++) {
            k = i + 1;
            k = k % 3;
            for (int j=0 ; j<3 ; j++) {
                l = j + 1;
                l = l % 3;
                tmp = intersection(A[i] , A[k] , B[j] , B[l]);
                if (tmp[0] == 1) {
                    Poly[NumberOfVertex][0] = tmp[1];
                    Poly[NumberOfVertex][1] = tmp[2];
                    NumberOfVertex++;
                }
                if (isVertexInside(B[j],A))
                {
                    Poly[NumberOfVertex] = B[j];
                    NumberOfVertex++;
                }
            }
            if (isVertexInside(A[i],B))
            {
                Poly[NumberOfVertex] = A[i];
                NumberOfVertex++;
            }
        }
        convexOfPoints(NumberOfVertex);
        double sq = polySquare(NumberOfVertex);
        System.out.print(NumberOfVertex);
        System.out.print(" ");
        System.out.println(sq);
    }
}