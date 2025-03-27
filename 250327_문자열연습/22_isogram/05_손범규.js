/*******************************
 * isogram : 중복글자 없는 단어
 * isogram 여부 판단하기 (true/false)
 */

const input = [
    "Dermatoglyphics", // -> true
    "programmer",      // -> false
    "Cocktail",         // -> false  대소문자 동일
    "isogram",         // -> true

    // 다른 입력 데이터로 테스트 필요하면, 배열에 데이터 더 넣어보고 실행해도 됩니다
]


function isIsogram(str) {
    const chars = str.toLowerCase().split('');
    const seen = [];

    for (let i = 0; i < chars.length; i++) {
        if (seen.includes(chars[i])) {
            return false; // we've seen this letter already
        }
        seen.push(chars[i]); // store it for later reference
    }
    return true; // no duplicates found!

// 리턴값: boolean

}

//        // [WAY 1]
//        str = str.toLowerCase();
//        for (int i = 0; i < str.length(); i++) {
//            if (str.substring(i+1).indexOf(str.charAt(i)) != -1) return false;
//        }
//        return true;

//        // [WAY 2]
//        str = str.toLowerCase();
//        char[] arr = str.toCharArray();
//        Arrays.sort(arr);
//
//        for (int i = 0; i < str.length() - 1; i++) {
//            if (arr[i] == arr[i + 1]) return false;
//        }
//        return true;

input.forEach(e => console.log(isIsogram(e)));