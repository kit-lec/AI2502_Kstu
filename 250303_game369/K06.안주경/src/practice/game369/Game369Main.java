package practice.game369;

import java.util.Scanner;

public class Game369Main {

	public static void main(String[] args) {
		System.out.println("369 게임");

		// 1부터 100까지 10개씩 줄바꿈하면서 출력
		for(int i = 1; i<=100 ; i+=10){
			for(int j = i; j<i+10; j++){ // 현재 줄에서 10개의 숫자를 출력
				int n = j; //자리수 검사 위해 변수 n에 j 값 대입
				boolean is369 = false; //3,6,9 포함돼있는지 판별할 변수

				//3,6,9 포함 여부 확인
				while (n > 0) { // 47일때 n : 47, 4 , 0,..
					int digit = n % 10; // 10으로 나눈 나머지 : 1의 자리 숫자
					if (digit == 3 || digit == 6 || digit == 9) {
						is369 = true; //3,6,9 포함 : is369 변수를 true로 바꾸고 while문 탈출
						break;
					}
					n /= 10; // 일의 자리 > 십의 자리 > 백의 자리로 이동해서 검사
				}

				if (is369==true) {
					System.out.print("* "); // 3, 6, 9 포함된 경우 * 출력
				} else { //is369가 true로 안바뀌면 (false면)
					System.out.print(j + " "); // 그렇지 않으면 원래 숫자 출력
				}

			}
			System.out.println();
		}

		// 사용자가 입력한 임의의 수 n에서 작동하려면 :
		Scanner sc = new Scanner(System.in);
		System.out.print("정수 num 입력: ");
		int num = sc.nextInt();

		for(int i = 1; i<=num ; i+=10){
			for(int j = i; j<i+10; j++){ // 현재 줄에서 10개의 숫자를 출력
				int n = j; //자리수 검사 위해 변수 n에 j 값 대입
				boolean is369 = false; //3,6,9 포함돼있는지 판별할 변수

				//3,6,9 포함 여부 확인
				while (n > 0) { // 47일때 n : 47, 4 , 0,..
					int digit = n % 10; // 10으로 나눈 나머지 : 1의 자리 숫자
					if (digit == 3 || digit == 6 || digit == 9) {
						is369 = true; //3,6,9 포함 : is369 변수를 true로 바꾸고 while문 탈출
						break;
					}
					n /= 10; // 일의 자리 > 십의 자리 > 백의 자리로 이동해서 검사
				}

				if (is369==true) {
					System.out.print("* "); // 3, 6, 9 포함된 경우 * 출력
				} else { //is369가 true로 안바뀌면 (false면)
					System.out.print(j + " "); // 그렇지 않으면 원래 숫자 출력
				}

			}
			System.out.println();
		}


	} // end main()

} // end class Game369Main








