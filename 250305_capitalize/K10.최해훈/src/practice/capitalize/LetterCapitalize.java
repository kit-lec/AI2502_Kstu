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
            // ※ 테스트 필요한 데이터 있으면 추가하셔도 좋습니다
    };

    // main 은 수정하지 마세요
    public static void main(String[] args) {
        for (var x : input) {
            System.out.println(letterCapitalize(x));
        }

    } // end main()

    public static String letterCapitalize(String str) {
        String result = "";

        String lower = str.toLowerCase();                       // 전부 소문자로 변경 i am a programmer
        String[] word = lower.trim().split("\\s+");      // 공백 기준으로 쪼갬 [i, am, a, programmer]

        String[] changeWord = new String[word.length];          // 첫글자 대문자로 바꾼 단어들을 저장할 배열

        for (int i = 0; i < word.length; i++) {
            String capital = word[i].substring(0, 1).toUpperCase();     // 각 단어 별 첫글자만 추출
            String change = String.join("", capital, word[i].substring(1)); // 대문자 + 나머지단어 (I, Am, A, Programmer)

            changeWord[i] = change; // 붙힌 단어 배열에 저장 [I, Am, A, Programmer]
        }

        result = String.join(" ", changeWord);

        return result;
    }  // end letterCapitalize()

} // end class

