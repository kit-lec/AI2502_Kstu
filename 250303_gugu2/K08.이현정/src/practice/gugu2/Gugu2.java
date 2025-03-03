package practice.gugu2;

import java.util.Scanner;

public class Gugu2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("시작단");
        int s = sc.nextInt();

        System.out.println("끝단");
        int e = sc.nextInt();

        System.out.println("시작 피승수");
        int a = sc.nextInt();

        System.out.println("끝 피승수");
        int b = sc.nextInt();

        System.out.println("출력할 열의수");
        int r = sc.nextInt();

        int cnt = e - s + 1;    // 몇 단이 될 지 구하기 위해 사용
        while (cnt > 0) {
            for (int i = a; i <= b; i++) {
                for (int j = s; j <= (s + (r - 1)); j++) {
                    if (j > e) continue;
                    String msg = String.format("%d x %d = %d", j, i, (i * j));
                    System.out.printf("%-14s", msg);    // 한 줄 당 14칸 지정, 왼쪽 정렬
                }
                System.out.println();
            }

            s += r;     // 다음으로 출력할 시작단 업데이트 (시작 피승수 + 열의 수)
            cnt -= r;   // 출력 완료한 개수만큼 감소
            System.out.println();
        }

        sc.close();
    } // end main()
} // end class
