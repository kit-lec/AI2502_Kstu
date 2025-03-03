package practice.gugu2;

import java.util.Scanner;

public class Gugu2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("시작단, 끝단, 시작 파승수, 끝 파승수, 출력 열수 차례대로 입력: ");
		int start = sc.nextInt(); //시작단
		int end = sc.nextInt(); //끝단
		int n1 = sc.nextInt(); //시작 피승수
		int n2 = sc.nextInt(); //끝 피승수
		int col = sc.nextInt(); // 출력 열수 >> 한 줄에 col 개씩 출력


		for (int i = start; i <= end; i+=col) { //start단부터 end단까지, col씩 건너뛰어야함
			for (int j = n1; j <= n2; j++) { //시.피~끝.피
				for (int k = i; k < i+col && k<=end; k++) { //단을 1씩 증가시키며 col 개만큼 옆으로 출력
				// k<= end: 첫번째열에서 끝날떄 그 옆으로 출력하지 X. 
						System.out.print(k + "X" + j + "=" + (k * j) + "\t");
					}
				System.out.println();
				}
			System.out.println();
			}



		sc.close();
	} // end main()
} // end class





