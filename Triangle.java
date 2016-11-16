
import java.util.Scanner;

public class Main {

    
    double x ; 
    double y ; 
    double z ; 
    
    
   static int countOfStyles = 0;
   static Main  Styles []  = new Main [100];
   
   Main(double x, double y , double z){
       this.z=Math.min(Math.min(x,y),z);
       this.x=Math.max(Math.max(x,y),z);
       double tmp1 = Math.max(x,y);
       double tmp2 = Math.max(Math.min(x,y),z);
       if (z>tmp1) { this.y = tmp1; }
       else {this.y = tmp2;}
   }
    
    public static void main(String []args){
        Main zero = new Main (0 , 0 , 0);
        Main tmp;
        double x;
        double y;
        double z;
        Scanner in = new Scanner(System.in);
        short n = in.nextShort();
        for (int i = 0 ; i < n ; i++){
            for (int j=0; j<100; j++){
                Main.Styles[j] = zero;
            }
            Main.countOfStyles = 0;
            x = in.nextDouble();
            y = in.nextDouble();
            z = in.nextDouble();
            tmp = new Main (x , y , z);
            rec(tmp);
            System.out.printf("Triangle %d: %d\n", i+1, Main.countOfStyles);
        }
    }
    
    static boolean check_style (Main tmp){
        boolean f = false;
        for (int i=0; i<Main.countOfStyles; i++){
            if ((Math.abs(Styles[i].x * tmp.y - tmp.x * Styles[i].y) < Math.pow(10,-10)) 
                    && (Math.abs(Styles[i].x * tmp.z - tmp.x * Styles[i].z) < Math.pow(10,-10))
                    && (Math.abs(Styles[i].z * tmp.y - tmp.z * Styles[i].y) < Math.pow(10, -10)) && (Styles[i].x!=0)){

                f = true;
                break;
            }
        }
        return f;
    } 
    
    static void rec (Main tmp){
        if (check_style(tmp) == true) { return; }
        else {
            Styles[Main.countOfStyles] = tmp;
            Main.countOfStyles++;
            double med = 2*tmp.y*tmp.y+2*tmp.z*tmp.z-tmp.x*tmp.x;
            med = Math.sqrt(med);
            med = 0.5 * med;
            Main tmp1 = new Main (0.5 * tmp.x , tmp.y , med);
            Main tmp2 = new Main (0.5 * tmp.x , tmp.z , med);
            rec(tmp1);
            rec(tmp2);
        }
    }

}
