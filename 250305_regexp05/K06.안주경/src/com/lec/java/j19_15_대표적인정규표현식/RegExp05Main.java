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

		String regex, input, title;
		String [] arrInput;
		
		//─────────────────────────────────────────
		title = "URL";
		regex = "^(https?|ftp)://[\\w.-]+(?:\\.[\\w.-]+)+[/\\w\\-._~:/?#\\[\\]@!$&'()*+,;=]*$";
		arrInput = new String[] {
				"https://www.google.com",
				"http://example.com",
				"ftp://files.server.net",
				"www.google.com",  // X
				"htp://invalid.com"  // X
		};
		System.out.println(title);
		regExpTest(regex, arrInput);


		//─────────────────────────────────────────
		title = "email";
		regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
		arrInput = new String[] {
				"test@example.com",
				"user123@domain.net",
				"hello.world@email.co.kr",
				"wrong-email.com",  // X
				"user@com"  // X
		};
		System.out.println(title);
		regExpTest(regex, arrInput);

		//─────────────────────────────────────────
		title = "주민등록번호";
		regex = "^[0-9]{6}-[1-4][0-9]{6}$";
		arrInput = new String[] {
				"980101-1234567",
				"000505-2345678",
				"123456-7890123",  // X
				"abcdef-1234567"  // X
		};
		System.out.println(title);
		regExpTest(regex, arrInput);
		
		//─────────────────────────────────────────
		title = "날짜 YYYY-MM-DD";
		regex = "^(19|20)\\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$";
		arrInput = new String[] {
				"2023-06-15",
				"1999-12-31",
				"2021-02-29",  // X (윤년이 아님)
				"20-12-25"  // X
		};
		System.out.println(title);
		regExpTest(regex, arrInput);
		
		//─────────────────────────────────────────
		title = "자연수";
		regex = "^[1-9]\\d*$";
		arrInput = new String[] {
				"1",
				"100",
				"9999",
				"0",  // X
				"-5"  // X
		};
		System.out.println(title);
		regExpTest(regex, arrInput);

		//─────────────────────────────────────────
		title = "정수";
		regex = "^-?[0-9]+$";
		arrInput = new String[] {
				"-10",
				"0",
				"12345",
				"+12",  // X ( + 부호는 매칭 안됨)
				"3.14"  // X
		};
		System.out.println(title);
		regExpTest(regex, arrInput);

		//─────────────────────────────────────────
		title = "소숫점 있는 실수";
		regex = "^-?[0-9]+(\\.[0-9]+)?$";
		arrInput = new String[] {
				"3.14",
				"-0.99",
				"100.0",
				"0",
				"abc"  // X
		};
		System.out.println(title);
		regExpTest(regex, arrInput);

		//─────────────────────────────────────────
		title = "소숫점 둘째자리까지";
		regex = "^-?[0-9]+(\\.[0-9]{1,2})?$";
		arrInput = new String[] {
				"3.1",
				"-0.99",
				"100.00",
				"3.141",  // X
				"123.456"  // X
		};
		System.out.println(title);
		regExpTest(regex, arrInput);

		//─────────────────────────────────────────
		title = "통화표시 (￦)";
		regex = "^￦[0-9,]+$";
		arrInput = new String[] {
				"￦1000",
				"￦1,000,000",
				"￦0",  // X (통화는 0원 불가능하다고 가정)
				"1000"  // X
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
