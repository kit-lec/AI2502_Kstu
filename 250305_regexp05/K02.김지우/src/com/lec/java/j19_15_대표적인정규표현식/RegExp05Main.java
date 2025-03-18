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
        String[] arrInput;

        //─────────────────────────────────────────
		title = "URL";
		regex = "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#()?&//=]*)";
		arrInput = new String[] {
			"https://www.dropbox.com/",
			"https://section.cafe.naver.com/ca-fe/home",
			"jiwoo000925@naver.com",
			"dropbox.com/"
		};
		System.out.println(title);
		regExpTest(regex, arrInput);


        //─────────────────────────────────────────
//        title = "email";
//        regex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
//                "[a-zA-Z0-9_+&*-]+)*@" +
//                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
//                "A-Z]{2,7}$";
//        arrInput = new String[]{
//                "jiwoo000925@naver.com",
//                "abc@hanmail.net",
//                "dropbox.com/"
//        };
//        System.out.println(title);
//        regExpTest(regex, arrInput);

        //─────────────────────────────────────────
//		title = "주민등록번호";
//		regex = "\\d{6}-[1-4]\\d{6}";
//		arrInput = new String[] {
//                "000925-4000000",
//                "532-4328957382"
//		};
//		System.out.println(title);
//		regExpTest(regex, arrInput);
        //─────────────────────────────────────────
//		title = "날짜 YYYY-MM-DD";
//		regex = "\\d{4}-[0-2][0-9]-[0-3][0-9]";
//		arrInput = new String[] {
//                "2000-09-25",
//                "1945-31-100"
//		};
//		System.out.println(title);
//		regExpTest(regex, arrInput);
        //─────────────────────────────────────────
//		title = "자연수";
//		regex = "[1-9]\\d*";
//		arrInput = new String[] {
//                "1",
//                "0",
//                "1231",
//                "-123"
//		};
//		System.out.println(title);
//		regExpTest(regex, arrInput);

        //─────────────────────────────────────────
//		title = "정수";
//		regex = "^[+-]?\\d+$";
//		arrInput = new String[] {
//			"0",
//            "-1",
//            "123",
//            "3.1"
//
//		};
//		System.out.println(title);
//		regExpTest(regex, arrInput);

        //─────────────────────────────────────────
//		title = "소숫점 있는 실수";
//		regex = "^[+-]?\\d+(\\.\\d+)$";
//		arrInput = new String[] {
//			    "1.2413",
//                "-0.432",
//                "1",
//                "-234",
//                "0.0"
//		};
//		System.out.println(title);
//		regExpTest(regex, arrInput);

        //─────────────────────────────────────────
//        title = "소숫점 둘째자리까지";
//        regex = "^[+-]?\\d+(\\.\\d{2})$";
//        arrInput = new String[]{
//                "1.23",
//                "2.3",
//                "-4.20"
//        };
//        System.out.println(title);
//        regExpTest(regex, arrInput);

        //─────────────────────────────────────────
//		title = "통화표시 (￦)";
//		regex = "\\￦\\d{4}"; // 예: 네자리 가격
//		arrInput = new String[] {
//                "￦4500",
//                "fdsffdsa",
//                "0123"
//		};
//		System.out.println(title);
//		regExpTest(regex, arrInput);

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

        if (Pattern.matches(regex, input)) {
            System.out.println("   ●매칭●");
        } else {
            System.out.println("   Ⅹ매칭 없슴Ⅹ");
        }

        System.out.println();
    } // end regExpTest()

} // end class
