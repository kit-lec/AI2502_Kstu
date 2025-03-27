/*******************************
 * 
 */

const input = [
    "all good tree", // ['a', 'l', ' ', 'g', 'o', 'd', ' ', 't', 'r', 'e']
    "AAA AAAA AA AAA",  // ['A', ' ', 'A' ,' ', 'A', ' ', 'A']
    "i AM a BOY",  // ['i', ' ', 'A', 'M',' ', 'a', ' ', 'B', 'O', 'Y']

    // 다른 입력 데이터로 테스트 필요하면, 배열에 데이터 더 넣어보고 실행해도 됩니다
]

function uniqueInOrder(str){
    // 반복되지 않는 문자만 저장, 대소문자 구분 O
    // 리턴값: 배열
    let arr = str.trim().split("");
    return arr.reduce((prev, cur) => {
        if (prev[prev.length - 1] === cur) return prev;

        prev.push(cur);
        return prev;
    }, [])
}

input.forEach(e => console.log(uniqueInOrder(e)));