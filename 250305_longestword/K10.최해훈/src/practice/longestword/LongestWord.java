package practice.longestword;

import java.util.Arrays;

/*******************************
 * 가장 긴 단어 찾기
 */

public class LongestWord {

    public static String[] input = {
            "I am a Student",     // ->  Student
            "That elephant is big",  // -> elephant
            // ※ 테스트 필요한 데이터 있으면 추가하셔도 좋습니다
    };

    // main 은 수정하지 마세요
    public static void main(String[] args) {
        for (var x : input) {
            System.out.println(longestWord(x));
        }
    }

    public static String longestWord(String str) {
        String result = "";

        String[] word = str.trim().split("\\s+");
        System.out.println(Arrays.toString(word));

        for (int i = 0; i < str.length(); i++) {
            if (word[i].length() < word[i + 1].length()) {
                result = word[i + 1];
            } else {
                result = word[i];
            }
        }


        return result;
    }
} // end class
















