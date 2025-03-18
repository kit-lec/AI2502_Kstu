package com.lec.java.j19_14_정규표현식연습;

/* 정규표현식 연습
 *
 * 이번에 우리 쇼핑몰에서 할인 쿠폰을 발행하려 한다.
 * 발행되는 쿠폰의 일련번호 형식은 다음과 같다.
 *
 *    알파벳2자리-숫자4자리-숫자3자리-알파벳3자리
 *       a  a  -  1  1  1  1  -  1  1  1  -  a  a  a
 *       0  1  2  3  4  5  6  7  8  9  10 11 12 13 14
 * 알파벳은 대소문자 구문 없슴 a-zA-Z
 * 숫자는 0으로 시작하면 안됨.
 * 사용자는 발급받은 쿠폰번호를 입력해야 하는데,
 * 위와 같은 형식만 받아들일수 있도록 만들자
 *
 * 허용예]
 * 	Ab-7890-786-zuy
 * 	ki-2010-893-Zip
 *
 * 불가]
 * 	xX-1200-089-zuy
 *  Ab-a890-b86-zuy
 * 	p9-324-389-zopl
 *  AAb-7890-786-zuyy
 *
 *  쿠폰번호를 계속해서 입력 받으면서
 * "유효한 쿠폰입니다"  혹은 "유효한 쿠폰이 아닙니다" 판정결과를 출력
 *
 * 'quit' 입력하면 프로그램 종료

 */

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExp04Main {

    public static void main(String[] args) {
        System.out.println("정규표현식 예제");

        String input;
        String regex = "^[a-zA-Z]{2}-[1-9][0-9]{3}-[1-9][0-9]{2}-[a-zA-Z]{3}$";
        Pattern pat = Pattern.compile(regex);
        Matcher matcher;
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("쿠폰번호를 입력하세요(종료는 quit 입력) : ");
            input = sc.nextLine();

            if (input.equalsIgnoreCase("quit")) break;

            matcher = pat.matcher(input);

            if (matcher.matches()) {
                System.out.println("는 유효한 쿠폰입니다.\n");
            } else {
                System.out.println("유효한 쿠폰이s 아닙니다.\n");
            }
        }
        sc.close();
        System.out.println("프로그램 종료");
    } // end main

} // end class




