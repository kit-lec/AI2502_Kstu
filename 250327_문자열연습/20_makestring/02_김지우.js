/*******************************
 * 단어의 앞글자로만 만들어진 문자열 생성
 */

const input = [
    "The community at Code States might be the biggest asset", // -> TcaCSmbtba
    "i am a PROGRAMMER",     // -> iaaP
    "THAT ELEPHANT IS BIG",  // -> TEIB

    // 다른 입력 데이터로 테스트 필요하면, 배열에 데이터 더 넣어보고 실행해도 됩니다
]

function makeString(str){
    let words = str.split(/\s+/);
    let firsts = [];
    for (w of words){
        firsts.push(w.slice(0,1));
    }
    return firsts.join("");
    // 리턴값: string
    
}

input.forEach(e => console.log(makeString(e)));