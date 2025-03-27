/*******************************
 * 가장 긴 단어 찾기
 */

const input = [
    "I am a Student",     // ->  Student
    "That elephant is big",  // -> elephant

    // 다른 입력 데이터로 테스트 필요하면, 배열에 데이터 더 넣어보고 실행해도 됩니다
]

function longestWord(str){

    // TODO : 이 함수내의 코드만! 작성하세요
    // 리턴값: string

    return str.split(/\s+/).reduce((a, b) => a.length > b.length ? a : b);
    // 공백기준으로 쪼개고, 그중에서 큰걸 고르는것
}

input.forEach(e => console.log(longestWord(e)));