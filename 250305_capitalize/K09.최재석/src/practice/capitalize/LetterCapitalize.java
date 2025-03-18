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

// 전체 소문자 변환, 쪼개기, 쪼갠것의 앞글자만 대문자 치환, 재결합


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

		System.out.println(str.toLowerCase());
		str = str.toLowerCase();  //  소문자 변환
		String[] split = str.split("\\s+");  // 문자열 나누기
		for (int i = 0; i < split.length; i++) {  // 첫글자만 대문자로 변환
			if (split[i].length() > 0);{
				split[i] = split[i].substring(0, 1).toUpperCase() + split[i].substring(1);
			}
		}
		return String.join(" ", split);


//		String[] capitalizedWords = new String[.length];
	}  // end letterCapitalize()

} // end class

