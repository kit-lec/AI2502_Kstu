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
        String[] arrInput;

        //─────────────────────────────────────────
        title = "URL";
        regex = "^(https?:\\/\\/)?([\\w.-]+)\\.([a-z]{2,6})(\\/[\\w.-]*)*$"; // TODO
        arrInput = new String[]{
                "https://www.google.com",
                "http://example.com",
                "www.github.com",
                "invalid-url"
        };
        System.out.println(title);
        regExpTest(regex, arrInput);


        //─────────────────────────────────────────
        title = "email";
        regex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$"; // TODO
        arrInput = new String[]{
                "test@example.com",
                "user.name@domain.co.kr",
                "invalid-email@com",
                "@missingusername.com"
        };
        System.out.println(title);
        regExpTest(regex, arrInput);

        //─────────────────────────────────────────
        title = "주민등록번호";
        regex = "^\\d{6}-[1-4](\\d){6}$"; // TODO
        arrInput = new String[]{
                "900101-1234567",
                "001231-2345678",
                "123456-7890123",
                "12345-6789012"
        };
        System.out.println(title);
        regExpTest(regex, arrInput);

        //─────────────────────────────────────────
        title = "날짜 YYYY-MM-DD";
        regex = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$";
        arrInput = new String[]{
                "2023-02-28",
                "1999-12-31",
                "2023-13-01",
                "2023-02-30"
        };
        System.out.println(title);
        regExpTest(regex, arrInput);

        //─────────────────────────────────────────
        title = "자연수";
        regex = "^[1-9]\\d*$"; // TODO
        arrInput = new String[]{
                "1",
                "100",
                "0",
                "-5" // TODO
        };
        System.out.println(title);
        regExpTest(regex, arrInput);

        //─────────────────────────────────────────
        title = "정수";
        regex = "^-?\\d+$"; // TODO
        arrInput = new String[]{
                "123",
                "-456",
                "0",
                "12.3"
        };
        System.out.println(title);
        regExpTest(regex, arrInput);

        //─────────────────────────────────────────
        title = "소숫점 있는 실수";
        regex = "^-?\\d*.\\d+$"; // TODO
        arrInput = new String[]{
                "3.14",
                "-0.5",
                "10",
                "12." // TODO
        };
        System.out.println(title);
        regExpTest(regex, arrInput);

        //─────────────────────────────────────────
        title = "소숫점 둘째자리까지";
        regex = "^-?\\d*.\\d{1,2}$"; // TODO
        arrInput = new String[]{
                "3.14",
                "-0.56",
                "10.123",
                "1.5"
        };
        System.out.println(title);
        regExpTest(regex, arrInput);

        //─────────────────────────────────────────
        title = "통화표시 (￦)";
        regex = "^￦\\d{1,3}(,\\d{3})*$"; // TODO
        arrInput = new String[]{
                "￦1,000",
                "￦100,000,000",
                "￦500",
                "1000"
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

        if (Pattern.matches(regex, input)) {
            System.out.println("   ●매칭●");
        } else {
            System.out.println("   Ⅹ매칭 없음Ⅹ");
        }

        System.out.println();
    } // end regExpTest()

} // end class
