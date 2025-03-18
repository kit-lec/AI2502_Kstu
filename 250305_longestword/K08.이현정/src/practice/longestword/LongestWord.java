package practice.longestword;

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
        for(var x : input){
            System.out.println(longestWord(x));
        }
    }

    public static String longestWord(String str) {
        String result = "";
        String[] words = str.split("\\s+");

        for (String word : words) {
            if (word.length() > result.length()) result = word;
        }

        return result;
    }
} // end class
