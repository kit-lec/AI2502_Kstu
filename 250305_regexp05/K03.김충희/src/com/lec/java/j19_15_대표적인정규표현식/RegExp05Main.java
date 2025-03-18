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
		regex = "^http[s]?://(www.)?[a-zA-Z0-9]{1,20}(.com/)$";
		 // http가 맨 앞에 있어야 하고 s는 있어도 되고 없어도 된다/ www.은 꼭 있어야 하며
		// 중간의 대소문자와 숫자의 범위의 경우 20까지 놓았다. .com/ 는 뒤에 무조건 들어 가야한다.
		arrInput = new String[] {
				"http://www.somehost.com/",
				"https://www.naver.com/",
				"https://www.google.com/",
				"https://regexr.com/"

		};
		System.out.println(title);
		regExpTest(regex, arrInput);

		
		//─────────────────────────────────────────
		title = "email";
		regex = "^[A-Za-z0-9](.*?)(@(gmail|naver|outlook).com)$";
		// 대소문자, 숫자가 앞에 있는지 / 중간에 @가 있는지/ 이메일 주소가 3개중 하나가 있는지 /뒤에 .com 이 있는지
		arrInput = new String[] {
			"Example@naver.com",
				"Johndoe123@gmail.com",
				"Susansmith@outlook.com",
				"Miketaylor@naver.com"
		};
		System.out.println(title);
		regExpTest(regex, arrInput);

		//─────────────────────────────────────────
		title = "주민등록번호";
		regex = "^[0-9]{1,2}[0-1]{1}[0-9]{1}[1-31]{2}(-)[1-4]{1}[0-9]{1,6}";
		//			연도 	/		월		/  일    /하이픈/ 성별/ 나머지
		arrInput = new String[] {
			"010622-3392893",
				"090122-1493892"
		};
		System.out.println(title);
		regExpTest(regex, arrInput);
		
		//─────────────────────────────────────────
		title = "날짜 YYYY-MM-DD";
		regex = "^[0-9]{4}|[0-9]{3}|[0-9]{2}[0-9]{1}(-)[0-1]{1}[0-9]{1}(-)[1-4]{1}[0-9]{1,6}$";
		// 			년도 									월 					일
		arrInput = new String[] {
			"888-08-22"
		};
		System.out.println(title);
		regExpTest(regex, arrInput);
		
		//─────────────────────────────────────────
		title = "자연수";
		regex = "^[1-9][0-9]*$"; // 1부터 9까지의 숫자가 있어야 하고 0 부터 9까지의 수가 있을 수 있다
		arrInput = new String[] {
				"1",
				"4000",
				"579"
		};
		System.out.println(title);
		regExpTest(regex, arrInput);

		//─────────────────────────────────────────
		title = "정수";
		regex = "^[0-9]{1,}|-[0-9]{1,}"; // 0과 음의 정수를 포함
		arrInput = new String[] {
			"101010",
				"2940",
				"0",
				"-1",
				"-100"
		};
		System.out.println(title);
		regExpTest(regex, arrInput);

		//─────────────────────────────────────────
		title = "소숫점 있는 실수";
		regex = "^[0-9]{1,}(.)[0-9]{1,}$"; // TODO
		arrInput = new String[] {
				"0.122",
				"0.314"

		};
		System.out.println(title);
		regExpTest(regex, arrInput);

		//─────────────────────────────────────────
		title = "소숫점 둘째자리까지";
		regex = "^[0-9]{1,}(.)[0-9]{2}$"; // TODO
		arrInput = new String[] {
				"0.22",
				"0.31",
				"12.34"
		};
		System.out.println(title);
		regExpTest(regex, arrInput);

		//─────────────────────────────────────────
		title = "통화표시 (￦)";
		regex = "^[1-9]{1}[0-9]{0,3}(?,[0-9]{3})*(￦)$";
		//
		arrInput = new String[] {
			"1,000￦",
				"500,000,000￦"
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
