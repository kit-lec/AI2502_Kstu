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
    let words = str.split(/\s+/);

    let joinWords = [];
    words.forEach(v =>{
        let split_v = v.split("");
        for(i = 0; i < split_v.length - 2; i++){
            for(j = i + 1; j < split_v.length - 1; j++){
                if(split_v[i] == split_v[j]) return;
                else joinWords.push(split_v[i]);
            }
        }
        return joinWords;
    })
    // 리턴값: 배열
}

input.forEach(e => console.log(uniqueInOrder(e)));