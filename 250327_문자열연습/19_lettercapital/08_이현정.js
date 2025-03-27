/*******************************
 * 영어문장에서 각 단어 첫글자만 대문자 만들기
 */

const input = [
    "i am a PROGRAMMER",     // -> I Am A Programmer
    "THAT ELEPHANT IS BIG",  // -> That Elephant Is Big

    // 다른 입력 데이터로 테스트 필요하면, 배열에 데이터 더 넣어보고 실행해도 됩니다
]

function letterCapitalize(str){
    // 리턴값: string
    let arr = str.trim().split(/\s+/);
    let result = "";
    for (word of arr){
        result += word.charAt(0).toUpperCase() + word.slice(1).toLowerCase() + " ";
    }
    return result;
}


input.forEach(e => console.log(letterCapitalize(e)));