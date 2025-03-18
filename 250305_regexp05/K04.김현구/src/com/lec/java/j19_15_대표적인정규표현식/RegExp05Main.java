package com.lec.java.j19_15_대표적인정규표현식;

import java.util.regex.Pattern;

/* 대표적인 정규 표현식 
 *  구글링 하면 대표적인 정규표현식들이 많이 구할수 있습니다.
 *  각 정규표현식들을 작성해보고
 *	매칭되는 문자열과 그렇지 않은 것들을 출력해봅시다.   
 */
public class RegExp05Main {

	public static void main(String[] args) {
		System.out.println("많이 쓰는 정규표현식");

		String regex, intput, title;
		String [] arrInput;
		
		//─────────────────────────────────────────
		title = "URL";
		regex = "^(https?|ftp)://[a-zA-Z0-9\\-\\.]+\\.[a-zA-Z]{2,3}(/\\S*)?$";
		arrInput = new String[] {
				"http://example.com",
				"https://example.com",
				"ftp://example.com",
				"example.com",  // 유효하지 않음
				"http://example",  // 유효하지 않음
		};
		System.out.println(title);
		regExpTest(regex, arrInput);

		
		//─────────────────────────────────────────
		title = "email";
		regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
		arrInput = new String[] {
				"example@example.com",
				"example@com",  // 유효하지 않음
				"example.com",  // 유효하지 않음
		};
		System.out.println(title);
		regExpTest(regex, arrInput);

		//─────────────────────────────────────────
		title = "주민등록번호";
		regex = "^\\d{6}-\\d{7}$";
		arrInput = new String[] {
				"123456-1234567",
				"12345-1234567",  // 유효하지 않음
				"123456-12345",    // 유효하지 않음
		};
		System.out.println(title);
		regExpTest(regex, arrInput);
		
		//─────────────────────────────────────────
		title = "날짜 YYYY-MM-DD";
		regex = "^\\d{4}-\\d{2}-\\d{2}$";
		arrInput = new String[] {
				"2023-03-05",
				"2023-3-5",  // 유효하지 않음
				"2023-03-32",  // 유효하지 않음
		};
		System.out.println(title);
		regExpTest(regex, arrInput);
		
		//─────────────────────────────────────────
		title = "자연수";
		regex = "^[0-9]+$";
		arrInput = new String[] {
				"123",
				"0",
				"-123",  // 유효하지 않음
				"12.34",  // 유효하지 않음
		};
		System.out.println(title);
		regExpTest(regex, arrInput);

		//─────────────────────────────────────────
		title = "정수";
		regex = "^[+-]?\\d+$";
		arrInput = new String[] {
				"123",
				"-123",
				"+123",
				"12.34",  // 유효하지 않음
		};
		System.out.println(title);
		regExpTest(regex, arrInput);

		//─────────────────────────────────────────
		title = "소숫점 있는 실수";
		regex = "^[+-]?\\d*\\.\\d+$";
		arrInput = new String[] {
				"123.45",
				"-123.45",
				"+123.45",
				"123",  // 유효하지 않음
		};
		System.out.println(title);
		regExpTest(regex, arrInput);

		//─────────────────────────────────────────
		title = "소숫점 둘째자리까지";
		regex = "^[+-]?\\d*\\.\\d{1,2}$";
		arrInput = new String[] {
				"123.45",
				"123.4",
				"-123.45",
				"+123.456",  // 유효하지 않음
		};
		System.out.println(title);
		regExpTest(regex, arrInput);

		//─────────────────────────────────────────
		title = "통화표시 (￦)";
		regex = "￦\\d+(,\\d{3})*(\\.\\d{1,2})?";
		arrInput = new String[] {
				"￦1,234",
				"￦1234",
				"￦1,234.56",
				"1,234",  // 유효하지 않음
		};
		
		System.out.println(title);
		regExpTest(regex, arrInput);

		System.out.println("프로그램 종료");

	} // end main()

	// 도우미 함수
	public static void regExpTest(String regex, String[] arrInput) {
		for (String input : arrInput)
			regExpTest(regex, input);
	}

	public static void regExpTest(String regex, String input) {
		System.out.println("[정규표현식 매칭 테스트]-----------------");
		System.out.println("정규표현식: " + regex);
		System.out.println("입력문자열: " + input);

		if(Pattern.matches(regex, input)) {
			System.out.println("   ●매칭●");
		} else {
			System.out.println("   Ⅹ매칭 없슴Ⅹ");
		}
		
		System.out.println();
	} // end regExpTest()

} // end class
