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

public class LetterCapitalize {

	public static final String[] input = {
			"i am a PROGRAMMER",     // -> I Am A Programmer
			"THAT ELEPHANT IS BIG",  // -> That Elephant Is Big
			// 다 소문자로 변환하고, 단어를 쪼개고, 맨 앞글자만 대문자로 바꾸고, 쪼갠걸 묶는다.
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
		str.toLowerCase();
		String[] arr = str.split(":");
		Arrays.toString(arr);
		System.out.println(str.replace(str.substring(0), str.toUpperCase()));

        return result;
    } // end letterCapitalize()

} // end class

