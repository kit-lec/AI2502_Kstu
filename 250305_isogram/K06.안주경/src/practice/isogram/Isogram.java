package practice.isogram;

/*******************************
 * isogram : 중복글자 없는 단어
 * isogram 여부 판단하기 (true/false)
 */

public class Isogram {

    public static final String[] input = {
            "Dermatoglyphics", // -> true
            "programmer",      // -> false
            "Cocktail",         // -> false  대소문자 구분 X
            "isogram",         // -> true
    };

    // main 은 수정하지 마세요
    public static void main(String[] args) {
        for (var word : input) {
            System.out.println(is_isogram(word));
        }
    }

	static boolean is_isogram(String str) {
		boolean result = true; // 기본값을 true로 설정 (isogram이면 true 반환)

		str = str.toLowerCase(); // 대소문자를 구분하지 않도록 소문자로 변환

		for (int i = 0; i < str.length(); i++) {
			for (int j = i + 1; j < str.length(); j++) {
				// 현재 문자와 이후 문자가 동일하면 중복이므로 false 반환
				if (str.charAt(i) == str.charAt(j)) {
					result = false;
					break;
				}
			}
		}

		return result;
	}

} // end class


