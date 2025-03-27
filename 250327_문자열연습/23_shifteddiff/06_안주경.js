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

function shiftedDiff(first, second) {
    if (first.length !== second.length) return -1;

    // 이어붙여서 hellohello 만들기
    const combined = first + first;

    // second가 combined 안에서 어디에서 시작하는지 찾음
    // 없으면 -1을 반환
    const index = combined.indexOf(second); //0 4 3 2 1

    // second가 hellohello안에 없으면
    if (index === -1) return -1;

    // 전체 길이 - index >> 5 1 2 3 4 나오는데 0 1 2 3 4 로 바꾸려면.. >> 5로 나눈 나머지
    return (first.length - index) % first.length;
}



input.forEach(e => console.log(shiftedDiff(e[0], e[1])));