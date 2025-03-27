/*******************************
 * isogram : 중복글자 없는 단어
 * isogram 여부 판단하기 (true/false)
 */

const input = [
    "Dermatoglyphics", // -> true
    "programmer",      // -> false
    "Cocktail",         // -> false  대소문자 동일
    "isogram",         // -> true

    // 다른 입력 데이터로 테스트 필요하면, 배열에 데이터 더 넣어보고 실행해도 됩니다
]

function isIsogram(str){
    // 리턴값: boolean
    let alpabets = str.trim().toLowerCase().split("");

    // 1. for 문 사용
    // for (let i = 0; i < alpabets.length; i++) {
    //     for (let j = i + 1; j < alpabets.length; j++) {
    //         if (alpabets[i] === alpabets[j]) { return false; }
    //     }
    // }
    // return true;

    // 2. reduce() 사용 -> 너무 더럽다,,,, // TODO : 더 깔끔하게 수정!!
    let result;
    alpabets.sort();
    return alpabets.reduce((a, b) => {
        if (!a || a === b) {
            result = false;
            return result;
        }
        if (alpabets.indexOf(b) === alpabets.length - 1) {
            if (!result) return true;
            return false;
        }
        return b;
        // result = (!a || a === b) ? false : b;
        // console.log(`${a}, ${b}, ${result}`);
        // return (alpabets.indexOf(b) === alpabets.length - 1) ? ((!result) ? false : true) : b;
    });
}

input.forEach(e => console.log(isIsogram(e)));