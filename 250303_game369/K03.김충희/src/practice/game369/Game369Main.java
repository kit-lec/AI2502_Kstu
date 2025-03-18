package practice.game369;

import java.util.Scanner;

public class Game369Main {

	public static void main(String[] args) {
		System.out.println("369 게임");
		Scanner sc = new Scanner(System.in);

		int count = sc.nextInt();
		int num = 0;
		while (num < count) {
			num++;
			String str = String.format("%d", num);
			for (int i = 0; i < str.length(); i++) {
				char ch = str.charAt(i);
				if (ch == '3') {
					str = "*";
				} else if (ch == '6') {
					str = "*";
				} else if (ch == '9') {
					str = "*";
				}
			}


			System.out.println(str);


			int count2 = sc.nextInt();
			int num2 = 0;
			while (num < count2) {
				num++;
				String str2 = String.valueOf(num2);
				for (int i = 0; i < str.length(); i++) {
					if (str2.substring(i, i + 1).equals("3")) {
						str = "*";
					} else if (str2.substring(i, i + 1).equals("6")) {
						str = "*";
					} else if (str2.substring(i, i + 1).equals("3")) {
						str = "*";
					}
				}


				System.out.println(str2);


			}


		} // end main()

	} // end class Game369Main
}








