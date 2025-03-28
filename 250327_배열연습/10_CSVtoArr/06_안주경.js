data = `height,weight,label
140,45,normal
145,72,fat
150,61,fat
137,56,fat
192,48,thin
175,77,fat`;
console.log(data);
/* 
    위 CSV 데이터를 아래와 같은 2차원배열로 만들기
    원소의 데이터 타입은 
      [number, number, string] 으로!
        
    [
        [140, 45, 'normal'],
        [145, 72, 'fat'],
        [150, 61, 'fat'],
        [137, 56, 'fat'],
        [192, 48, 'thin'],
        [175, 77, 'fat']
    ] 
 */

// TODO :  reduce 복습하고 reduce 써서 하기.. *** (map, filter 조합도 가능)

//for, if 써서
// 1. 줄 단위로 나누기
let rows = data.trim().split('\n');

// 2. 결과를 담을 배열
let result = [];

// 3. 첫 줄 건너뛰고 1번 인덱스부터 시작
for (let i = 0; i < rows.length; i++) {
    if (i === 0) continue; // 첫 줄은 건너뛰기
    //rows[i]로 각 줄 반복
    let cols = rows[i].split(','); // 콤마 기준 나눠서 cols 배열에 담음

    // 문자열 → 숫자로 변환
    let height = Number(cols[0]);
    let weight = Number(cols[1]);
    let label = cols[2];

    // 배열로 묶어서 result 배열에 push
    result.push([height, weight, label]);
}

console.log(result);





















