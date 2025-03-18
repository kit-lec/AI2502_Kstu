package practice.isogram;

/*******************************
 * isogram : 중복글자 없는 단어
 * isogram 여부 판단하기 (true/false)
 */

public class Isogram {

    public static final String[] input = {
            "isogram",         // -> true
            "Dermatoglyphics", // -> true
            "programmer",      // -> false
            "Cocktail",         // -> false  대소문자 동일
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

        str = str.toLowerCase();

        for (int i = 0; i < str.length(); i++) {
            String temp1 = str.substring(i, i + 1);

            for (int j = i + 1; j < str.length(); j++) {
                String temp2 = str.substring(j, j + 1);
                if (temp1.equals(temp2)) {
                    return result;
                }
            }
        }
        result = true;

        return result;
    }
} // end class

// p r o g r a m m e r  -> length = 10
// 0 1 2 3 4 5 6 7 8 9
// 0 : 1~9 | 1 : 2~9 | 2 : 3~9 | 3 : 4~9 | 4 : 5~9 | ... | 7 : 8~9 | 8 : 9