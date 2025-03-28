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
// 1, 일단 줄을 \n 로 split 해주고
//2, 맨 위에 있는 height, weight 지우고
//3, 각 단어들끼리 나누고
//4, 키와 몸무게 값을 number로 변환한다.



let result = data.split("\n").slice(1).map(row => row.split(",")).map(row => [Number(row[0]),Number(row[1]),row[2]]);
console.log(result);

// 방법2
result = data.split("\n").slice(1).reduce((a,b) => {
    let row = b.split(",");
    a.push([Number(row[0]),Number(row[1]),row[2]]);
    return a
},[]);
console.log(result);

// 방법3
result = data.split("\n").slice(1).reduce((a,b) => {
    let rows = b.split(",");
    for (e of rows) {
        return Number(row[0]),Number(rows[1]),rows[2];
    }

},[]);
console.log(result);






















