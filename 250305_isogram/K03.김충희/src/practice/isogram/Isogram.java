package practice.isogram;

import java.util.Arrays;

/*******************************
 * isogram : 중복글자 없는 단어
 * isogram 여부 판단하기 (true/false)
 */

public class Isogram {

    public static final String[] input = {
            "Dermatoglyphics", // -> true
            "programmer",      // -> false
            "Cocktail",         // -> false  대소문자 동일
            "isogram",
            "myieun"
            // -> true
            // ※ 테스트 필요한 데이터 있으면 추가하셔도 좋습니다
    };

    // main 은 수정하지 마세요
    public static void main(String[] args) {
        for (var word : input) {
            System.out.println(is_isogram(word));
        }
    }

    static boolean is_isogram(String str) {
        boolean result = false;
        // 1, 단어들을 하나씩 쪼개기
        // 2, 두개의 변수를 만들고 하나의 변수는 하나의 단어만, 다른 변수는 그 하나의 단어를 제외한 배열을 만듦
        // 2, 쪼갠 단어 변수와 배열 단어 간에 서로 다른 단어가 있는지 없는지 확인하기
        // 3, 다른 단어가 있으면 true로 변환


        str = str.toLowerCase();
        for (int i = 0; i <str.length(); i++) {
            String s = str.substring(i, i+1);
            for (int j = i+1; j < str.length(); j++) {
                if (s.equals( str.substring(j , j+1))) {
                    return result;
                }
            }
        }
        result = true;






            return result;

        } // end class
}
