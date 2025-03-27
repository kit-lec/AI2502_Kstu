/*******************************
 * 영어문장에서 각 단어 첫글자만 대문자 만들기
 */

const input = [
    "i am a PROGRAMMER",     // -> I Am A Programmer
    "THAT ELEPHANT IS BIG",  // -> That Elephant Is Big

    // 다른 입력 데이터로 테스트 필요하면, 배열에 데이터 더 넣어보고 실행해도 됩니다
]

function letterCapitalize(str) {

    // TODO : 이 함수내의 코드만! 작성하세요
    let result = str.toLowerCase().split(" ");
    for (i = 0; i < result.length; i++) {
        result[i] = result[i].charAt(0).toUpperCase() + result[i].slice(1);
    }
    return result.join(" ");
}

input.forEach(e => console.log(letterCapitalize(e)));

/**
 * LowerCase - split : ['i', 'am', 'a', 'programmer']
 * slice(1) - m - rogrammer
 * chatAt(0).toUpperCase I A A P
 * join
 */
