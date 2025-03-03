package practice.gugu2;

import java.util.Scanner;

public class Gugu2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("시작단을 입력해주세요: ");
        int start_dan = sc.nextInt();
        System.out.println("끝단을 입력해주세요: ");
        int end_dan = sc.nextInt();
        if (start_dan > end_dan) {
            System.out.println("시작단은 끝단보다 작거나 같아야 합니다.\n다시 시도해주세요.");
            return;
        }
        System.out.println("시작 피승수를 입력해주세요: ");
        int start_mul = sc.nextInt();
        System.out.println("끝 피승수를 입력해주세요: ");
        int end_mul = sc.nextInt();
        if (start_mul > end_mul) {
            System.out.println("시작피승수는 끝피승수보다 작거나 같아야 합니다.\n다시 시도해주세요.");
            return;
        }
        System.out.println("출력할 열의수를 입력해주세요: ");
        int columns = sc.nextInt();
        if (columns < 1) {
            System.out.println("출력할 열의수는 1보다 같거나 커야 합니다.\n다시 시도해주세요.");
            return;
        }


        // 구구단 ver.2
        //
        // n개 열로 출력될수 있도록
        // 작성해보세요

        for (int sd = start_dan; sd <= end_dan; sd++) {
            for (int sm = start_mul; sm <= end_mul; sm++) {
                for (int n = columns; n <= columns; n++) {
                    System.out.println(sd + " x " + sm + " = " + (sd * sm));
                }
            }
            System.out.println();
        }






        sc.close();
    } // end main()
} // end class































