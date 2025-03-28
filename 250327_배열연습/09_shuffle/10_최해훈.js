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

let result = [];
let temp = names.split('\n').sort().slice(2);

for (i = temp.length; i > 0; i--) {
    let idx = Math.floor(Math.random() * i);
    result.push(temp[idx]);
}
A

console.log(result);