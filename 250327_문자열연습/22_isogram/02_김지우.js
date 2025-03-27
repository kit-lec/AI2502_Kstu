/*******************************
 * isogram : 중복글자 없는 단어
 * isogram 여부 판단하기 (true/false)
 */

const input = [
    "Dermatoglyphics", // -> true
    "programmer",      // -> false
    "Cocktail",         // -> false  대소문자 동일
    "isogram",         // -> true
    "ahhhhh",
    "abcde"

    // 다른 입력 데이터로 테스트 필요하면, 배열에 데이터 더 넣어보고 실행해도 됩니다
]

function isIsogram(str){

    let letters = str.toLowerCase().split("");
    for(i = 0; i < letters.length - 2; i++){
        for(j = i + 1; j < letters.length - 1; j++){
            if(letters[i] == letters[j]) return false;
        }
    }

    return true;
    // 리턴값: boolean

}

input.forEach(e => console.log(isIsogram(e)));