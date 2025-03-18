package practice.game369;

import java.util.Scanner;

public class Game369Main {

	public static void main(String[] args) {
		System.out.println("369 게임");
		for (int i = 1; i <= 100; i++) {
			boolean j = false;  // 3,6,9 가 있는지 없는지 판단하기 위해 j 선언 (false -> 3,6,9 없음. / true -> 3,6,9 포함 되어 있음.)

			for(int num = i; num > 0; num = num / 10 ) {    //  ->> 십의 자리 확인하기
				int last = num % 10;  // ->> 일의 자리 확인하기

				if (last == 3 || last == 6 || last == 9){  //  ||(or) 사용해서 3,6,9 포함 확인하기
					j = true;
				}
			}

			if (j == true) {
				System.out.print(" * ");
			}
			else {
				System.out.print( i + " ");
			}

			if (i % 10 == 0){      // 열번째 숫자마다 줄 바꿈
				System.out.print("\n");
			}





		}










	} // end main()

} // end class Game369Main








