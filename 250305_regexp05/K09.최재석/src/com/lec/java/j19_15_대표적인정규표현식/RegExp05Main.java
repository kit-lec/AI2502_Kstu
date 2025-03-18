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
		regex = "^((http|https)://)?(www.)?([a-zA-Z0-9]+)\\.[a-z]+([a-zA-Z0-9.?#]+)?";
		arrInput = new String[] {
				"http://10.10.64.33:8080/eVoiceV6",
				"https://tvrs.tms/adm/eVoiceV6",
				"http://www.naver.co.kr"
		};
		System.out.println(title);
		regExpTest(regex, arrInput);

		
		//─────────────────────────────────────────
		title = "email";
		regex = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
		arrInput = new String[] {
			"gearteamgearteam@gmail.com",
			"wwioejflaksdjf@naver.com",
		};
		System.out.println(title);
		regExpTest(regex, arrInput);

		//─────────────────────────────────────────
		title = "주민등록번호";
		regex = "^(\\d{2})(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])-[1-4]\\d{6}$"; // 8자리 생년월일
		arrInput = new String[] {
			"870707-1029389",
			"530729-2987190"
		};
		System.out.println(title);
		regExpTest(regex, arrInput);
		
		//─────────────────────────────────────────
		title = "날짜 YYYY-MM-DD";
		regex = "^\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$";
		arrInput = new String[] {
			"2024-09-01",
			"YYYY-MM-DD",
			"2012-09-22",
			"1945-08-15"
		};
		System.out.println(title);
		regExpTest(regex, arrInput);
		
		//─────────────────────────────────────────
		title = "자연수"; // 0보다 큰 정수
		regex = "^[1-9]\\d*$";
		arrInput = new String[] {
    			"1",
				"19",
				"192740192470974444",
				"-27",
				"Finest-Autoever"
			};
		System.out.println(title);
		regExpTest(regex, arrInput);

		//─────────────────────────────────────────
		title = "정수";
		regex = "^[0-9]+$";
		arrInput = new String[] {
			    "0",
				"000",
				"876",
				"-763"
		};
		System.out.println(title);
		regExpTest(regex, arrInput);

		//─────────────────────────────────────────
		title = "소숫점 있는 실수";
		regex = "^[+-]?\\d*\\.\\d+$";
		arrInput = new String[] {
			    "34.5",
				"24.9",
				"145"
		};
		System.out.println(title);
		regExpTest(regex, arrInput);

		//─────────────────────────────────────────
		title = "소숫점 둘째자리까지";
		regex = "^-?\\d+\\.\\d{1,2}$";
		arrInput = new String[] {
			    "12.45",
				"12.876",
				"17.2",
				"19"
		};
		System.out.println(title);
		regExpTest(regex, arrInput);

		//─────────────────────────────────────────
		title = "통화표시 (￦)";
		regex = "[₩￦]\\d+(,\\d{3})*"; // 통화 기호(₩,￦), 금액을 포함
		arrInput = new String[] {
			    "1,000 ￦",
				"5,670,000￦",
				"5800￦"
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
