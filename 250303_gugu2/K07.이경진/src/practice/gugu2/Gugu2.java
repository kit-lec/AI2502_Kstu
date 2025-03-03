package practice.gugu2;

import javax.swing.*;
import java.util.Scanner;

public class Gugu2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        for (int dan = 3; dan <= 13; dan++) {
            System.out.println(dan);

            for (int mul = 6; mul <= 11; mul++) {
                System.out.println(dan + "*" + mul + "=" + dan * mul);
            }
            {
                if ((dan + 4) % 5 == 1)
                    System.out.println('\t' + (dan + 4) % 5);

                int startRow = 3; // 시작 단
                int endRow = 13; // 끝 단
                int startCol = 6; // 시작 피승수
                int endCol = 11; // 끝 피승수
                int columns = 5; // 출력할 열의 수



                for (int i = startRow; i <= endRow; i += columns) {

                }

                {



                    System.out.println();
                }

            }
            sc.close();
        } // end main()
    }
}// end class































