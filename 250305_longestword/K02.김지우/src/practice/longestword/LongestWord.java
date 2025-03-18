package practice.longestword;

/*******************************
 * 가장 긴 단어 찾기
 */

public class LongestWord {

    public static String[] input = {
            "I am a Student",     // ->  Student
            "That elephant is big",  // -> elephant
            "The longest abcdefg"
            // ※ 테스트 필요한 데이터 있으면 추가하셔도 좋습니다
    };

    // main 은 수정하지 마세요
    public static void main(String[] args) {
        for(var x : input){
            System.out.println(longestWord(x));
        }
    }

    // 1. 쪼갠 후 2. 단어의 길이를 계산 3. 최대 숫자 구하기
    public static String longestWord(String str) {
        String result = "";

        String[] splits = str.trim().split("\\s+"); // 모든 공백 형식 빼고 단어 단위로 배열에 저장

        int longest = splits[0].length();
        for (int i = 1; i < splits.length; i++) {
             if (splits[i].length() > longest) {
                 longest = splits[i].length();
                 result = splits[i];
             }
        }

        return result;
    }
} // end class
















