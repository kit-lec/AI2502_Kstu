package practice.game369;

import java.util.Scanner;

public class Game369Main {

	public static void main(String[] args) {
		System.out.println("369 게임");
		// 초기값 설정
		int start = 1;
//		int end = 100;

		Scanner sc = new Scanner(System.in);
		int end = sc.nextInt();

		// 검색 참조: 입력받은 수의 총 자릿수 구하기 위함
		int n = Integer.toString(end).length();

		int rowNum = 1;

		// 반복문
		for (int i = start; i <= end; i+= 10) {
			// 다음 행을 따질 때 열은 첫번째부터 따지므로 colNum 초기화
			int colNum = 0;

			// 10 개씩 출력
			for (int j = i; j < i + 10; j++) {
				colNum++;

				// 1. 열: 3, 6, 9 번째일 때는 열 전부가 1의 자리가 3, 6, 9 즁 포함하는 것 존재
				if (colNum == 3 || colNum == 6 || colNum == 9) {
					System.out.printf("%" + n + "s ", '*');
				}
				// 2. 행: 4, 7, 10 / 14, 17, 20 / ... 번째 행은 1의 자리 이후에 3, 6, 9 중 포함하는 것 존재
				// 후자 조건은 맨 마지막 중 3, 6, 9 안 들어가는 것이 * 처리되는 것 방지 (40, 70.. 과 같은 것)
				else if ((rowNum % 10 == 4 || rowNum % 10 == 7 || rowNum % 10 == 0) && (j % 10 != 0))
					System.out.printf("%" + n + "s ", '*');
				// 3. 가장 마자막 열 중 3, 6, 9 에 해당하는 수들의 행번호가 3, 6, 9/ 13, 16, 19...
				else if((rowNum % 10 == 3 || rowNum % 10 == 6 || rowNum % 10 == 9) && (j % 10 == 0))
					System.out.printf("%" + n + "s ", '*');
				// 4. 나머지는 해당 없으므로 원래 숫자를 출력
				else
					System.out.printf("%" + n + "d ", j);

				if (j == end) break;
			}
			//한 행이 10개이므로 10개 다돌고 다음 행에 대한 rowNum 증가
			rowNum++;
			System.out.println(); // 10개 찍고 개행
		}
		System.out.println();
	} // end main()

} // end class Game369Main








