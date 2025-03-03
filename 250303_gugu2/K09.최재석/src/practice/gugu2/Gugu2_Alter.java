package practice.gugu2;

import java.util.Scanner;

public class Gugu2_Alter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 구구단 출력 : 중첩 for 사용
        for(int dan = 1; dan < 10; dan++){   // x1 ~ x9
            for(int mu1 = 2; mu1 < 10; mu1++){   // 2단 ~ 9단
                System.out.print(mu1 + " x " + dan + " = " + mu1 * dan + "\t");

//                if(mu1=4 * dan=9){System.out.print("\n");}

            }
            System.out.println();
        }






    // 보존
//        for(int dan = 2; dan < 10; dan++){   // 2단 ~ 9단
//            for(int mu1 = 1; mu1 < 10; mu1++){   // x1 ~ x9
//                System.out.println(dan + " x " + mu1 + " = " + (dan * mu1));
//            }
//            System.out.println();
//        }


        sc.close();
    }
}
