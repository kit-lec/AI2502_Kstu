package practice.game369;

import java.util.Scanner;

public class Game369Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("369 게임");

		// 임의의 수
		System.out.print("자연수를 입력하세요 > ");
		int n = sc.nextInt();
		boolean flag = true;
//
		for (int i = 1; i <= n; i++) {
			flag = print369(i);

			if (i > 1 && i % 10 == 1) System.out.println();

			if (flag) continue;
			else if(i < 10) System.out.print(i + "\t");

			if(i >= 10) {
				int j = i /10;
				flag = print369(j);

				if (flag) continue;
				else System.out.print(i + "\t");
			}
		}

	} // end main()

	private static boolean print369(int i) {
		switch (i % 10) {
			case 3, 6, 9:
				System.out.print("*\t");
				return true;
			default:
//				System.out.print(i + "\t");
				return false;
		}
	}

} // end class Game369Main
