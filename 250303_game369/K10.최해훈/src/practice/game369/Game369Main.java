package practice.game369;

import java.util.Scanner;

public class Game369Main {

    public static void main(String[] args) {
        System.out.println("369 게임");

        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();

//        // 100까지
//        for (int i = 1; i <= number; i++) {
//            if (i % 10 == 3 || i % 10 == 6 || i % 10 == 9 || i / 10 == 3 || i / 10 == 6 || i / 10 == 9) {
//                char ch = '*';
//                System.out.printf("%2s\t", ch);
//            } else {
//                System.out.printf("%3d ", i);
//            }
//
//            if (i % 10 == 0) {
//                System.out.println();
//            }
//        }

        // 100 이상
        // num : 1의자리
        for (int i = 1; i <= number; i++) {
            for (int num = i; num > 0; num /= 10) {

                if (num % 10 == 3 || num % 10 == 6 || num % 10 == 9) {
                    char star = '*';
                    System.out.printf("%6s", star);
                    break;
                }

                if (num < 10) {
                    System.out.printf("%6d", i);
                }
            }
            if (i % 10 == 0) {
                System.out.println();
            }
        }


    } // end main()

} // end class Game369Main