// 배열 섞기 (shuffle)

let names = `
강준우
권희수
김동호
김미란
김종선
김주희
김창희
김효은
서현기
송민호
신철희
유상곤
이기원
이상진
이승원
이승준
이종현
임승빈
장고운
전광현
조재환
최강현
최정인
최진무
허지우
`;

// result 에는 랜덤으로 섞인 문자열 배열이 담기게 한다
/*  예시
[
  '전광현', '허지우', '신철희',
  '최진무', '장고운', '최정인',
  '이상진', '최강현', '김창희',
  '서현기', '임승빈', '이기원',
  '권희수', '송민호', '조재환',
  '강준우', '김종선', '이승준',
  '이종현', '김미란', '김동호',
  '유상곤', '김효은', '김주희',
  '이승원'
]
*/


//일단 빈 배열 하나 만들어서 저 거대 문자열을 배열로 바꿈맨
let original = [];
original = names.trim().split(/\s+/);
// 총 25개
// console.log(original.length);

let indexChoice = []; // 랜덤에 사용할 인덱스 목록 만들 것임
// 얘네 중에 골라진 애 있으면 지우는 식으로.. 여기있는 애들은 아직 사용 ㄱㄴ
let random = []; // 최종 리턴할 랜덤으로 섞인 배열


for(i = 0; i < original.length; i++) {
    indexChoice.push(i);
} // 0 ~ 24

// console.log(indexChoice);

// 25개 중 하나를 랜덤으로 뽑고 걔는 indexChoice 에서 지우고..
// 나머지 24개중 뽑고..또 지우고 23 개중... 이런식으로 반복?하면 될듯

// Math.random() : 0 이상 1미만의 난수를 발생시킴 -> 25를 곱하면?
// Math.random() * 25 는 0이상 25미만 난수 -> 정수만 나오게 하려면? Math.floor() 얘는
// 소수점 버리고 작은 정수들 중 얘랑 가장 가까운 정수로 내놓는다. 예) 24.999 -> 24

function makeRandom() {
    while (true) {
        index = Math.floor(Math.random() * 25) // 0부터 24까지의 정수만 나오게 만들어냄
        if(indexChoice.indexOf(index) != -1){ // 랜덤으로 돌려서 나온애가 이 목록에 있으면 사용가능~
            random.push(original[index]); //원래 배열에 이 index 에 해당하는 값을 리턴할 랜덤 배열에 넣어주고
            indexChoice.splice(indexChoice.indexOf(index), 1);
            // 쓴 index 값은 또 나오면 안되니 목록에서 삭제 ㅇㅇ
        }

        if(indexChoice.length == 0) break; //0부터 24까지 다써서 없으면 다 랜덤으로 돌려서 넣은거니까 break
    }

    return random;
}

let result = makeRandom();
console.log(result);
