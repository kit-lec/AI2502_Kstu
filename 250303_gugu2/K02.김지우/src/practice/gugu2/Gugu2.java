package practice.gugu2;

import java.util.Scanner;

public class Gugu2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("시작단");
        int start = sc.nextInt();
        System.out.println("끝단");
        int end = sc.nextInt();
        System.out.println("시작 피승수");
        int startMul = sc.nextInt();
        System.out.println("끝 피승수");
        int endMul = sc.nextInt();
        System.out.println("출력할 열의 수");
        int numOfColumn = sc.nextInt();

//        for (int i = startMul; i <= endMul ; i++) {
//
//            int k = start;
//            int count = 0;
//            while(k <= end){
//                System.out.print(k + " X " + i + " = " + (k * i) + "\t");
//                k++;
//                count++;
//
//                if(count % numOfColumn == 0)
//                    System.out.println();
//
//            }
//
//            System.out.println();
//        }

        // 3번째 줄마다 피승수 1씩 증가
        // 최종 피승수까지 끝나면 단 증가


        int cnt = 0;
        for (int j = startMul; j < endMul; j++) {
            int i = start;

            while (i <= end){
                System.out.print(i + " X " + j + " = " + (i * j) + "\t");
                i++;
                cnt++;

                if (cnt % numOfColumn == 0){
                    continue;
                }
            }
        }




        sc.close();
    } // end main()
} // end class































