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

let dataArr = data.trim().split(/\s+/);
dataArr.splice(0, 1);

// 1. map 사용
let result = dataArr.map(cur => {
	let info = cur.split(',');
	return [Number(info[0]), Number(info[1]), info[2]];
});

// 2. reduce 사용 -> map 이 더 깔끔한 듯
// let result = dataArr.reduce((prev, cur) => {
// 	let info = cur.split(',');
// 	prev.push([Number(info[0]), Number(info[1]), info[2]]);
// 	return prev;
// }, []);

console.log(result);

console.log(" \n[프로그램 종료]");
