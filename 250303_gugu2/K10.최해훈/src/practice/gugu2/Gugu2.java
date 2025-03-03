package practice.gugu2;

import java.util.Scanner;

public class Gugu2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

//        시작단         start1
//        끝단           end1
//        시작 피승수     m
//        끝 피승수       n
//        출력할 열의 수  count

        System.out.print("시작단 : ");
        int start1 = sc.nextInt();

        System.out.print("끝단 : ");
        int end1 = sc.nextInt();

        System.out.print("시작 피승 수 : ");
        int start2 = sc.nextInt();

        System.out.print("끝 피승 수 : ");
        int end2 = sc.nextInt();

        System.out.print("출력할 열의 수 : ");
        int count = sc.nextInt();


        for (int j = start2; j <= end2; j++) {
            int k = 0;
            for (int i = start1; i <= end1; i++) {
                System.out.printf("%d * %d = %2d\t\t", i, j, i * j);
                k++;

                if (count == k) {
                    System.out.println();
                    k = 0;
                }
            }
            System.out.println();
        }
    }
}































