/*******************************
 * 
 */

const input = [
    "all good tree", // ['a', 'l', ' ', 'g', 'o', 'd', ' ', 't', 'r', 'e']
    "AAA AAAA AA AAA",  // ['A', ' ', 'A' ,' ', 'A', ' ', 'A']
    "i AM a BOY",  // ['i', ' ', 'A', 'M',' ', 'a', ' ', 'B', 'O', 'Y']

    // 다른 입력 데이터로 테스트 필요하면, 배열에 데이터 더 넣어보고 실행해도 됩니다
]

function uniqueInOrder(str) {
    const result = [];

    for (let i = 0; i < str.length; i++) {
        // 처음 문자이거나, 이전 문자와 다를 때만 추가
        if (i === 0 || str[i] !== str[i - 1]) {
            result.push(str[i]);
        }
    }

    return result;
}


input.forEach(e => console.log(uniqueInOrder(e)));