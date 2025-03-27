/*******************************
 * 문자열 시프팅 차이값
 */

const input = [
// [first, second]
    ["hello", "hello"],    // 0
    ["hello", "ohell"],    // 1
    ["hello", "lohel"],    // 2
    ["hello", "llohe"],    // 3
    ["hello", "elloh"],    // 4
    ["hello", "elohl"],    // -1  ←shift 문자열이 아닌 경우

    // 다른 입력 데이터로 테스트 필요하면, 배열에 데이터 더 넣어보고 실행해도 됩니다
]

function shiftedDiff(first, second){
    // o + hell, lo + hel, llo + he, ello + h
    let num;
    if(first == second) num = 0;
    else if(second == first.slice(4).concat(first.slice(0,4))) num = 1;
    else if(second == first.slice(3).concat(first.slice(0,3))) num = 2;
    else if(second == first.slice(2).concat(first.slice(0,2))) num = 3;
    else if(second == first.slice(1).concat(first.slice(0,1))) num = 4;
    else num = -1;

    return num;
    // 리턴값: 정수
}

input.forEach(e => console.log(shiftedDiff(e[0], e[1])));
// first = "hello";
// second = 'ohell';
// console.log(first.slice(4).concat(first.slice(0,4)));