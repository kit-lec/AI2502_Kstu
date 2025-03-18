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

		String regex, title;
		String [] arrInput;
		
		//─────────────────────────────────────────
		title = "URL";
		regex = "^((http[s]?|ftp):\\/\\/)?(?:www\\.)?[-a-zA-Z0-9@:%._\\+~#=가-힣]{1,256}[:|\\.][a-zA-Z0-9()]{1,6}\\b(?:[-a-zA-Z0-9()@:%_\\+,.~#?&\\/=가-힣]*)$";
		arrInput = new String[] {
				"https://jolly-sally.tistory.com/157",	// true
				"https://youtube.com/",					// true
				"https://www.naver.com/",				// true
		};
		System.out.println(title);
		regExpTest(regex, arrInput);

		
		//─────────────────────────────────────────
		title = "email";
		regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		arrInput = new String[] {
				"lhj020731@gmail.com",	// true
				"mamam0731@naver.com",	// true
				"mamam2094@daum.com"	// true
		};
		System.out.println(title);
		regExpTest(regex, arrInput);

		//─────────────────────────────────────────
		title = "주민등록번호";
		regex = "^\\d{2}(0[1-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])-[1-4]\\d{6}$";
		arrInput = new String[] {
				"020731-4234567",	// true
				"930419-2040291",	// true
				"670301-1356821",	// true
				"040110-3194375"	// true
		};
		System.out.println(title);
		regExpTest(regex, arrInput);
		
		//─────────────────────────────────────────
		title = "날짜 YYYY-MM-DD";
		regex = "(^[1-2]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2]\\d|3[0-1])$)";
		arrInput = new String[] {
				"2025-03-05",	// true
				"2024-10-31",	// true
				"1999-07-22",	// true
				"1845-12-10"	// true
		};
		System.out.println(title);
		regExpTest(regex, arrInput);
		
		//─────────────────────────────────────────
		title = "자연수";
		regex = "^[1-9]\\d*";
		arrInput = new String[] {
				"1",		// true
				"94257",	// true
				"23578086",	// true
				"-23",		// false
				"1.7"		// false
		};
		System.out.println(title);
		regExpTest(regex, arrInput);

		//─────────────────────────────────────────
		title = "정수";
		regex = "^-?[1-9]\\d*";
		arrInput = new String[] {
				"135627",	// true
				"-123",		// true
				"7",		// true
				"-1.1"		// false
		};
		System.out.println(title);
		regExpTest(regex, arrInput);

		//─────────────────────────────────────────
		title = "소숫점 있는 실수";
		regex = "((^-?[1-9]\\d*).\\d+)";
		arrInput = new String[] {
				"3.14",				// true
				"36485.13456860",	// true
				"66"				// false
		};
		System.out.println(title);
		regExpTest(regex, arrInput);

		//─────────────────────────────────────────
		title = "소숫점 둘째자리까지";
		regex = "((^-?[1-9]\\d*).\\d{2})";
		arrInput = new String[] {
				"3.14",				// true
				"2.1",				// true
				"36485.13456860"	// false
		};
		System.out.println(title);
		regExpTest(regex, arrInput);

		//─────────────────────────────────────────
		title = "통화표시 (￦)";
		regex = "^￦[1-9]\\d*";
		arrInput = new String[] {
				"￦1000",	// true
				"€30",		// false
				"¥500",		// false
				"80원"		// false
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
