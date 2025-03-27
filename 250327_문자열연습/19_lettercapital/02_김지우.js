/*******************************
 * 영어문장에서 각 단어 첫글자만 대문자 만들기
 */

const input = [
    "i am a PROGRAMMER",     // -> I Am A Programmer
    "THAT ELEPHANT IS BIG",  // -> That Elephant Is Big
    "A bC def",
    // 다른 입력 데이터로 테스트 필요하면, 배열에 데이터 더 넣어보고 실행해도 됩니다
]

function letterCapitalize(str){
    str_lower = str.toLowerCase();
    let words = str_lower.split(/\s+/);
    for(i = 0; i < words.length; i++){
        words[i] = words[i].charAt(0).toUpperCase() + words[i].slice(1);
    }

    return words.join(" ");
    // 리턴값: string
}

input.forEach(e => console.log(letterCapitalize(e)));