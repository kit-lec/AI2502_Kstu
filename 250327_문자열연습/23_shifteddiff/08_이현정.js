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
    // 리턴값: 정수
    if (first.length != second.length) return -1;
    if (first === second) return 0;

    for (let i = 1; i <= first.length - 1; i++) {
        if (first == second.slice(i) + second.slice(0, i)) return i;
    }
    return -1;
}

input.forEach(e => console.log(shiftedDiff(e[0], e[1])));