data = `height,weight,label
140,45,normal
145,72,fat
150,61,fat
137,56,fat
192,48,thin
175,77,fat`;
// console.log(data);
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

let arr = data.split('\n');

let result = [];
for (i = 1; i < arr.length; i++) {
    let temp = arr[i].split(',');
    let height = Number(temp[0]);
    let weight = Number(temp[1]);
    let label = temp[2];
    result.push([height, weight, label]);
}

console.log(result);

// let result = arr.slice(1).reduce((a, b) => {
//     let temp = [];
//     temp = arr.split(',');
//     a.push([Number(temp[0]), Number(temp[1]), temp[2]]);
//     return a;
// }, []);
//
// console.log(result);

/**
 * [] [140, 45, 'normal']
 *
 *
 */