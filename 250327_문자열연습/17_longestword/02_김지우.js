/*******************************
 * 가장 긴 단어 찾기
 */

const input = [
    "I am a Student",     // ->  Student
    "That elephant is big",  // -> elephant

    // 다른 입력 데이터로 테스트 필요하면, 배열에 데이터 더 넣어보고 실행해도 됩니다
]

function longestWord(str){
    let words = str.split(/\s+/); // 단어 단위로 쪼개셈 -> 단어들의 배열이 나옴
    for(i = 0; i < words.length - 2; i++){
        for(j = i + 1; j < words.length - 1; j++){
            if(words[i].length < words[j].length) return words[j];
        }
    }
}

input.forEach(e => console.log(longestWord(e)));
