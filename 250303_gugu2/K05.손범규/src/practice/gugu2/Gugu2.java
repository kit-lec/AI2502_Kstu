package practice.gugu2;

import java.util.Scanner;

public class Gugu2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int x_start, x_end, y_start, y_end, column;



        System.out.println("시작단");
        x_start = sc.nextInt();
        System.out.println("끝단");
        x_end = sc.nextInt();
        System.out.println("시작 피승수");
        y_start = sc.nextInt();
        System.out.println("끝 피승수");
        y_end = sc.nextInt();
        System.out.println("출력할 열의 수");
        column = sc.nextInt();

        for (int i = x_start; i <= x_end; i+=column){
                for (int j = y_start; j <= y_end; j++) {
                    for (int k = i; k < i + column; k++) {
                        System.out.printf("%2d * %2d = %3d \t", k, j, j * k);
                        if (k==x_end) break;
                    }
                    System.out.println();
                }
                System.out.println();
                }

        sc.close();
    } // end main()
} // end class































