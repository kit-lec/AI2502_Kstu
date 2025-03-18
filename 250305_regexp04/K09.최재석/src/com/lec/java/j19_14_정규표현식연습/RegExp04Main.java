package com.lec.java.j19_14_정규표현식연습;

import java.util.Scanner;

/* 정규표현식 연습
 * 
 * 이번에 우리 쇼핑몰에서 할인 쿠폰을 발행하려 한다.
 * 발행되는 쿠폰의 일련번호 형식은 다음과 같다.
 * 
 *    알파벳두자리-숫자4자리-숫자3자리-알파벳3자리 
 * 
 * 알파벳은 대소문자 구문 없슴
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

public class RegExp04Main {

	public static void main(String[] args) {
		System.out.println("정규표현식 예제");
		Scanner sc = new Scanner(System.in);
		
		while(true){
			System.out.println("쿠폰 번호를 입력하세요:");
			String input = sc.nextLine();

			if(input.equalsIgnoreCase("quit")){  // 프로그램 종료용 quit 명령문 입력
				break;
			}
			checkcoupon(input);
		}
		
		sc.close();
		System.out.println("프로그램 종료");
	} // end main

	public static void checkcoupon(String coupon) {
		String regex = "(?i)^[A-Z]{2}-[1-9][0-9]{3}-[1-9][0-9]{2}-[A-Z]{3}$";  // 유효한 쿠폰 범위 지정하는 정규표현식
		if (coupon.matches(regex)) {
			System.out.println("유효한 쿠폰입니다.");
		} else {
			System.out.println("유효한 쿠폰이 아닙니다.");
		}
	}


} // end class










