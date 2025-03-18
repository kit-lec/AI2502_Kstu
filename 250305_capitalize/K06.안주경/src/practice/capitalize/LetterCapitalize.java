package practice.capitalize;

import java.util.Scanner;

/* LetterCapitalize
 * 	주어진 문자열에서, 공백으로 구분된 각 단어의 앞 문자만 대문자로 만들기
 *
 * 	[입력예]
 * 		hello my WORLD
 *  [출력예]
 * 		Hello My World
 */

public class LetterCapitalize {

	public static final String[] input = {
			"i am a PROGRAMMER",     // -> I Am A Programmer
			"THAT ELEPHANT IS BIG",  // -> That Elephant Is Big
			// ※ 테스트 필요한 데이터 있으면 추가하셔도 좋습니다
	};

	// main 은 수정하지 마세요
	public static void main(String[] args) {
		for(var x : input){
			System.out.println(letterCapitalize(x));
		}

	} // end main()

	public static String letterCapitalize(String str){
		String result = "";
		//1.lowercase 2.쪼갠 후 3.앞에꺼 대문자로 > 그다음에 묶어(join)
		str = str.toLowerCase();
		String[] splited = str.trim().split("\\s+"); //i,am,a,programmer
		for(int i = 0; i < splited.length; i++){
			// 첫번째문자를 대문자로 바꾼 후 나머지는 그대로(소문자로) 이어붙여야돼. substring 반환결과는 첫글자1개라서
			splited[i]=splited[i].substring(0, 1).toUpperCase()+splited[i].substring(1);
			}//I, Am, A, Programmer
		System.out.println(String.join(" ", splited)); // delimiter로 이으라는 뜻


		return result;
	}  // end letterCapitalize()

} // end class

