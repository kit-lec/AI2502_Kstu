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

// TODO : 
//  reduce 활용!  (map, filter 조합도 가능)
//  정 힘들면 클래식하게 for, if 로도 해보시구요.

const arr = data.trim().split(/\s+/);
const result = arr.slice(1).reduce((a, b) => {  // a -> 이전까지 누적된 결과값, b -> 배열에서 지금 처리중인 요소
    const sep = b.split(',');  // 현재 줄을 쉼표로 나눠서 sep 에 저장
    const row = [sep[0], sep[1], sep[2]];  // 2차원 배열이므로 배열안에 배열을 또 만든다
    a.push(row);  // 만든 row 을 a 에 추가하기
    return a;
}, []);



console.log(result);




// console.log("\n[프로그램 종료]", '\n'.repeat(20));






















