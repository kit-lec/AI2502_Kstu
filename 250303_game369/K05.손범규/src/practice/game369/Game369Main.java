package practice.game369;

import java.util.Scanner;

public class Game369Main {

    public static void main(String[] args) {
        System.out.println("369 게임");

        Scanner scanner = new Scanner(System.in);

//		int num;
//		num = 100;
//		for (int i = 0; i < 10; i++) {
//			for (int j = 1; j <= 10; j++) {
//				int k = 10 * i + j;
//				if (i != 3 && j != 3) {
//					System.out.printf("%3d ", k);
//				} else {
//					char star = '*';
//					System.out.printf("%3c ", star);
//				}
//			}
//			System.out.println();
//		}

        int number = scanner.nextInt();
        int line = 0;

        for (int k = 0; k <= number; k++) {
            int num = k;
            boolean check = false;
            while (num > 0) {
                int digit = num % 10;
                if (digit == 3 || digit == 6 || digit == 9) {
                    check = true;
                    break;
                }
                num /= 10;
            }
            if (check) {
                System.out.printf("%3c ", '*');
            } else {
                System.out.printf("%3d ", k);
            }

            line++;
            if (line % 10 == 0) {
                System.out.println();
            }


        }


    } // end main()

} // end class Game369Main








