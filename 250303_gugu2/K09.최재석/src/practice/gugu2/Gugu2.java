package practice.gugu2;

import java.util.Scanner;

public class Gugu2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        // 2단
//        int mul2 = 1;
//        while(mul2 < 10){
//            System.out.print("2 x " + mul2 + " = " + (2 * mul2) +"\t");
//            System.out.println();
//            mul2++;
//        }


        // 3단
//        int mul3 = 1;
//        while(mul3 < 10){
//            System.out.print("3 x " + mul3 + " = " + (3 * mul3));
//            System.out.println();
//            mul3++;
//        }
//        System.out.println();


//        for(int dan=1; dan<=9; dan++){
//            for(int mu1=2; mu1<=9; mu1++){
//                System.out.print(mu1+"*"+dan+"="+mu1*dan+"\t");



//        for(int dan = 1; dan < 10; dan++){
//            for(int mu1 = 2; mu1 < 10; mu1++){
//                System.out.print(mu1 + " x " + dan + " = " + mu1 * dan + "\t");
//            }System.out.println();
//        }



//        for(int dan = 2; dan < 10; dan++){
//            for(int mu1 = 1; mu1 < 10; mu1++){
//                System.out.print(dan + " x " + mu1 + " = " + (dan * mu1) + "\t");
//            }
//            System.out.print("\n");
//        }





        // 최종 결과물
        for(int dan = 1; dan < 10; dan++){
            for(int mu1 = 2; mu1 < 5; mu1++){
                System.out.print(mu1 + " x " + dan + " = " + mu1 * dan + "\t");
            }System.out.println();
        }
        System.out.println();

        for(int dan = 1; dan < 10; dan++){
            for(int mu1 = 5; mu1 < 8; mu1++){
                System.out.print(mu1 + " x " + dan + " = " + mu1 * dan + "\t");
            }System.out.println();
        }
        System.out.println();

        for(int dan = 1; dan < 10; dan++){
            for(int mu1 = 8; mu1 < 10; mu1++){
                System.out.print(mu1 + " x " + dan + " = " + mu1 * dan + "\t");
            }System.out.println();
        }


        sc.close();
    } // end main()
} // end class































