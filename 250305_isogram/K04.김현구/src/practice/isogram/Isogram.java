package practice.isogram;

import java.util.Scanner;

/*******************************
 * isogram : 중복글자 없는 단어
 * isogram 여부 판단하기 (true/false)
 */

public class Isogram {

    public static final String[] input = {
            "Dermatoglyphics", // -> true
            "programmer",      // -> false
            "Cocktail",         // -> false  대소문자 동일
            "isogram",         // -> true
            // ※ 테스트 필요한 데이터 있으면 추가하셔도 좋습니다
    };

    // main 은 수정하지 마세요
    public static void main(String[] args) {
        for (var word : input) {
            System.out.println(is_isogram(word));
        }
    } // end main

    static boolean is_isogram(String str) {
        boolean result = false;
        Scanner sc = new Scanner(str);



        return result;
    }
} // end class






