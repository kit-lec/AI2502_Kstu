/*******************************
 * 문자열 좌우 반전
 */

const input = [
    "abcde",            // edcba
    "I am a Student",   // tnedutS a ma I
]

function reverse(str){
    // TODO : 이 함수내의 코드만! 작성하세요
    return str.split('').reverse().join('');
}

input.forEach(e => console.log(reverse(e)));