package practice.capitalize;

import java.util.Arrays;
import java.util.Scanner;

/* LetterCapitalize
 * 	주어진 문자열에서, 공백으로 구분된 각 단어의 앞 문자만 대문자로 만들기
 *
 * 	[입력예]
 * 		hello my WORLD
 *  [출력예]
 * 		Hello My World
 */
//1, 단어 마다 분리 한다
// 2, 단어의 배열을 만들고 맨 앞 글자만 추출을 한다
// 추출한 맨 앞글자를 upperCase 로 만들어 준다

public class LetterCapitalize {

	public static final String[] input = {
			"i am a PROGRAMMER",     // -> I Am A Programmer
			"THAT ELEPHANT IS BIG",
			"i am Iron MaN"// -> That Elephant Is Big
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

		String[] n = str.toLowerCase().trim().split("\\s+");//각 단어 마다 분리

		for (int i = 0; i < n.length; i++) {
			String temp = n[i].toUpperCase().charAt(0) + n[i].substring(1).toLowerCase();
			// 			각 문장의 첫 단어를 대문자로 변환    +     그 이후의 값들은 소문자로
 			result += temp + " ";  //temp에 있는 문장을 result에 추가
			}
		return result;
	}  // end letterCapitalize()

} // end class

