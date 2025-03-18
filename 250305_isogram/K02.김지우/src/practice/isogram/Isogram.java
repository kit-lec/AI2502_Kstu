package practice.isogram;

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
            "IwantTogoHome",
            "Bye"
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

        String lowerWord = str.trim().toLowerCase(); // 우선 모두 소문자로 변환

        for (int i = 0; i < lowerWord.length() - 1; i++) { // i를 한 개씩 증가. 비교한 앞 문자들은 제끼고 다음문자를 첫기준으로.
            String firstChar = lowerWord.substring(i, i + 1); // 첫 번째 문자
            String remainChars = lowerWord.substring(i + 1); // 나머지 문자

            if(remainChars.indexOf(firstChar) != -1) { // -1이 아니라는 것은 같은 문자 존재
                result = false;
                break; //iso가 아니므로 반복문 중단
            }

            result = true; // 같은 문자가 없으면 true로 유지 후 끝까지 반복문 수행
        }

        return result;
    }
} // end class

