package practice.capitalize;

import org.w3c.dom.ls.LSOutput;

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

	// 1. 전부 소문자로 변환 2. 단어로 쪼갠 후 3. 첫 번째만 대문자로 변환 4. 다시 합치기
	public static final String[] input = {
			"i am a PROGRAMMER",     // -> I Am A Programmer
			"THAT ELEPHANT IS BIG",  // -> That Elephant Is Big
			"uppercase test hello"
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

		String[] words = str.trim().split("\\s+"); // 문장을 단어 단위로 쪼개서 넣기
		for(int i = 0; i < words.length; i++){
			words[i] = words[i].toLowerCase(); // 우선 전부 소문자로 변환

			var firstLetter = words[i].substring(0, 1).toUpperCase(); // substring 을 통해 첫번째 문자만 대문자로 변경
			words[i] = firstLetter.concat(words[i].substring(1)); // concat 을 통해 첫번째 문자와 그 이후를 다시 합쳐준다
		}

		result = String.join(" ", words); // 완성된 단어들을 다시 합침

		return result;
	}  // end letterCapitalize()

} // end class

