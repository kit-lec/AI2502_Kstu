package practice.gugu2;

import java.util.Scanner;

public class Gugu2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("시작단");
        int start = sc.nextInt();
        System.out.println("끝단");
        int end = sc.nextInt();
        System.out.println("시작 파승수");
        int numStart = sc.nextInt();
        System.out.println("끝 파승수");
        int numEnd = sc.nextInt();
//        System.out.println("출력할 열의수");
//        int ouputLine = 5;
//        start = 3;
//        end = 5;
//        numStart =2;
//        numEnd = 7;

            for (int i = start; i <= end ; i++) {
                System.out.println();
                if (start == end + 1) {
                    break;
                }
                for (i = numStart; i <= numEnd; i++) {
                        System.out.println(start + " X " + i + " = " + (start * i));
                }
                start++;
            }

        sc.close();
    } // end main()
} // end class































