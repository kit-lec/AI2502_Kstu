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

// 문자열을 줄 단위로 나눈 후, 빈 문자열 제거
let nameArray = names.trim().split('\n'); //공백 제거 후 줄바꿈 기준 문자열을 잘라 배열로 만들어
                                                            //nameArray 배열에 담음

// Fisher-Yates Shuffle 알고리즘
for (let i = nameArray.length - 1; i > 0; i--) { // i는 배열 마지막 인덱스부터 1까지 감소
    const j = Math.floor(Math.random() * (i + 1)); //j = 0~i 사이의 랜덤 인덱스
    [nameArray[i], nameArray[j]] = [nameArray[j], nameArray[i]]; //i 번째 요소와 j 번째 요소 교체
}
// Math.floor(Math.random() * (i + 1)) → 0부터 i까지 중 랜덤하게 정수 하나 뽑기

/*
* ✅ Math.random() * (i + 1) >> 0부터 i사이 정수
e.g. i=4 일 때,
Math.random() * (4 + 1)   →   Math.random() * 5
0 이상 5 미만의 소수 만듦. >> 이걸 floor() 하면 0~4 사이 정수.
>> 0.73 * 5 = 3.65
>> 0.12 * 5 = 0.6
>> 0.98 * 5 = 4.9
*/

let result = nameArray;

console.log(result);