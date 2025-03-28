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
result = data.split(/\n/).reduce((prev,curr, idx) => {
    if (idx === 0) return prev;
    const [h, w, label] = curr.split(',');
    prev.push([+h, +w, label]);
    return prev;}, []);

console.log(result);


console.log("\n[프로그램 종료]", '\n'.repeat(20));






















