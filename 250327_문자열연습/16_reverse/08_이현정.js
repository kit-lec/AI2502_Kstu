/*******************************
 * 문자열 좌우 반전
 */

const input = [
    "abcde",            // edcba
    "I am a Student",   // tnedutS a ma I

    // 다른 입력 데이터로 테스트 필요하면, 배열에 데이터 더 넣어보고 실행해도 됩니다
]

function reverse(str){
    // 리턴값: string
    let arr = str.split("");
    return arr.reverse().join("");
}

input.forEach(e => console.log(reverse(e)));