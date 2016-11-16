import static java.lang.Double.isNaN;

import java.util.Scanner;
import java.util.*;

public class Main {

    static boolean RectanglesIntersects(double x1, double y1, double x2,
                                        double y2, double x3, double y3,
                                        double x4, double y4) {
        return (x3 - x2) * (x4 - x1) <= 0 && (y3 - y2) * (y4 - y1) <= 0;
    }

    static boolean intersect(double x1, double y1, double x2, double y2,
                             double x3, double y3, double x4, double y4) {
        double ABx = x2 - x1;
        double ABy = y2 - y1;
        double ACx = x3 - x1;
        double ACy = y3 - y1;
        double ADx = x4 - x1;
        double ADy = y4 - y1;
        double CAx = x1 - x3;
        double CAy = y1 - y3;
        double CBx = x2 - x3;
        double CBy = y2 - y3;
        double CDx = x4 - x3;
        double CDy = y4 - y3;
        double ACxAB = ACx * ABy - ACy * ABx;
        double ADxAB = ADx * ABy - ADy * ABx;
        double CAxCD = CAx * CDy - CAy * CDx;
        double CBxCD = CBx * CDy - CBy * CDx;
        if (!RectanglesIntersects(Math.min(x1, x2), Math.min(y1, y2),
                Math.max(x1, x2), Math.max(y1, y2),
                Math.min(x3, x4), Math.min(y3, y4),
                Math.max(x3, x4), Math.max(y3, y4)))
            return false;
        return ACxAB * ADxAB <= 0 && CAxCD * CBxCD <= 0;
    }


    static double[] intersection(double[] A, double[] B,
                                 double[] C, double[] D) {
        double[] intersection = new double[3];
        if (intersect(A[0], A[1], B[0], B[1], C[0], C[1], D[0], D[1])) {
            intersection[0] = 1;
            double det;
            det = (B[0] - A[0]) * (C[1] - D[1]) -
                    (B[1] - A[1]) * (C[0] - D[0]);
            intersection[1] = ((C[0] - A[0]) * (C[1] - D[1]) -
                    (C[1] - A[1]) * (C[0] - D[0])) / det;
            intersection[2] = intersection[1];
            intersection[1] = A[0] + (B[0] - A[0]) * intersection[1];
            intersection[2] = A[1] + (B[1] - A[1]) * intersection[2];
        } else {
            intersection[0] = 0;
        }
        return intersection;
    }

    static void convexOfPoints(int num, double[][] poly) {
        int k;
        double tmp;
        double sign;
        boolean f;
        k = 0;
        int i = 1;
        while (i < num) {
            sign = 0;
            f = true;
            for (int j = 0; j < num; j++) {
                if ((j != i) && (j != k)) {
                    tmp = (poly[i][1] - poly[k][1]) *
                            (poly[j][0] - poly[k][0]) -
                            (poly[i][0] - poly[k][0]) *
                                    (poly[j][1] - poly[k][1]);
                    if (sign * tmp < 0) {
                        f = false;
                    }
                    sign = tmp;
                }
            }
            if (f) {
                double[] t = new double[2];
                t[0] = poly[i][0];
                t[1] = poly[i][1];
                poly[i][0] = poly[k + 1][0];
                poly[i][1] = poly[k + 1][1];
                poly[k + 1][0] = t[0];
                poly[k + 1][1] = t[1];
                k++;
                i = k + 1;
            } else {
                i++;
            }
        }
    }

    static boolean isVertexInside(double[] A, double[][] B) {
        double product;
        double sign = 0;
        int k;
        for (int i = 0; i < 3; i++) {
            k = i + 1;
            k = k % 3;
            product = (B[i][0] - A[0]) * (B[k][1] - B[i][1]) -
                    (B[i][1] - A[1]) * (B[k][0] - B[i][0]);
            if (sign * product < 0) {
                return false;
            }
            sign = product;
        }
        return true;
    }

    static double polySquare(int num, double[][] poly) {
        double s = 0;
        for (int i = 0; i < num - 1; i++) {
            s = s + (poly[i + 1][1] + poly[i][1]) *
                    (poly[i + 1][0] - poly[i][0]) * 0.5;
        }
        s = s + (poly[0][1] + poly[num - 1][1]) *
                (poly[0][0] - poly[num - 1][0]) * 0.5;
        s = Math.abs(s);
        return s;
    }

    static boolean notInPoly(double x, double y,
                             int NumberOfVertex, double[][] poly) {
        for (int i = 0; i < NumberOfVertex; i++) {
            if ((poly[i][0] == x) && (poly[i][1] == y)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        double a[][] = new double[3][2];
        double b[][] = new double[3][2];
        double poly[][] = new double[6][2];
        int NumberOfVertex;
        NumberOfVertex = 0;
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            a[i][0] = sc.nextInt();
            a[i][1] = sc.nextInt();
        }
        for (int i = 0; i < 3; i++) {
            b[i][0] = sc.nextInt();
            b[i][1] = sc.nextInt();
        }
        for (int i = 0; i < 6; i++) {
            poly[i][0] = -101;
            poly[i][1] = -101;
        }
        double[] tmp = new double[3];
        int k;
        int l;
        for (int i = 0; i < 3; i++) {
            k = i + 1;
            k = k % 3;
            for (int j = 0; j < 3; j++) {
                l = j + 1;
                l = l % 3;
                tmp = intersection(a[i], a[k], b[j], b[l]);
                if ((tmp[0] == 1) &&
                        notInPoly(tmp[1], tmp[2], NumberOfVertex, poly) &&
                        !isNaN(tmp[1]) && !isNaN(tmp[2])) {
                    poly[NumberOfVertex][0] = tmp[1];
                    poly[NumberOfVertex][1] = tmp[2];
                    NumberOfVertex++;
                }
            }
            if (isVertexInside(a[i], b) &&
                    notInPoly(a[i][0], a[i][1], NumberOfVertex, poly) &&
                    !isNaN(a[i][0]) && !isNaN(a[i][1])) {
                poly[NumberOfVertex] = a[i];
                NumberOfVertex++;
            }
            if (isVertexInside(b[i], a) &&
                    notInPoly(b[i][0], b[i][1], NumberOfVertex, poly) &&
                    !isNaN(b[i][0]) && !isNaN(b[i][1])) {
                poly[NumberOfVertex] = b[i];
                NumberOfVertex++;
            }
        }
        convexOfPoints(NumberOfVertex, poly);
        double sq = 0;
        if (NumberOfVertex > 2) sq = polySquare(NumberOfVertex, poly);
        System.out.print(NumberOfVertex);
        System.out.print(" ");
        System.out.printf(Locale.US, "%2.2f\n", sq);
    }
}