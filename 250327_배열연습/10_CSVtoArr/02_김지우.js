data = `height,weight,label
140,45,normal
145,72,fat
150,61,fat
137,56,fat
192,48,thin
175,77,fat`;
console.log(data);
console.log('-'.repeat(20));
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

let original = data.trim().split(/\s+/);
// console.log(array);
// 첫번째 값은 칼럼명 나타내는 것 같으니 지워줍시다
original.splice(0, 1);
// console.log(original); // 일단 한 줄씩 문자열로 잘라 배열로 들어간당
// console.log('-'.repeat(20));

let array = [];

for(v of original){
    v_arr = v.split(','); // 한 줄마다의 문자열을 쉼표기준으로 쪼개 배열로바꿔줘서 다시 넣기
    array.push(v_arr);
}

// console.log(array);
// console.log('-'.repeat(20));

array_map = array.map(item => [Number(item[0]), Number(item[1]), item[2]]);
console.log(array_map);



// 한 줄이 한 string 덩어리로 되어있으니 배열로 바꿔줘야...


console.log("[프로그램 종료]", '\n'.repeat(20));






















