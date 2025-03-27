/*******************************
 * 가장 긴 단어 찾기
 */

const input = [
    "I am a Student",     // ->  Student
    "That elephant is big",  // -> elephant

    // 다른 입력 데이터로 테스트 필요하면, 배열에 데이터 더 넣어보고 실행해도 됩니다
]

function longestWord(str){

    return str.trim().split(' ')
        .sort((w1,w2) => w2.length - w1.length)[0];
    // 리턴값: string

}

input.forEach(e => console.log(longestWord(e)));