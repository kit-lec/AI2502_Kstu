package practice.wordcount;

import java.util.*;

/* 1] 문서(문자열) 안의 단어의 빈도수를 계수해서 출력하기
 * 	- 대소문자 구분하지 않기 :   The 와 the 는 같은 단어
 *  - 2글자 이상만 계수하기
 *  - 구두점/기호 ",.\"\'`!?;:-()" 잘라내기
 *  - 공백 짤라내기
 * ex)
 * 	an : 234
 * 	the : 314
 * ...
 * 
 * hint]
 * 	split("\\s+")  --> String[]   
 * 	--> StringTokenizer  (혹은 정규표현식)
 *  	  --> HashMap<String, Integer>   <단어, 빈도수>  사용
 * ───────────────────────────────────────────────────────────    
 * 2] 빈도수 내림차순으로 정렬하여 출력하기 (상위 20개만 출력)
 * 	ex)
 *	1 the:113개
 *	2 she:95개
 *	3 to:85개
 *	...   
 *
 * hint]
 * 	Comparable<> 이나 Comparator<> 적용
 */

// TODO : 필요한 객체들 작성
// hint> 빈도수 담을 객체, Comparator<> ..

public class AliceInWonderland {

	public static void main(String[] args) {		
		System.out.println("실습: 단어 발생 빈도");
		HashMap<String, Integer> hmap = new HashMap<String, Integer>();

		// 제공된 문장을 소문자로 변경 후 공백기준으로 단어를 분리하기
		String [] words = C.ALICE30.trim().toLowerCase().split("\\s+");

		// 발생빈도 작성
		for(String word : words) {  // 정규 표현식 메소드에 replaceAll 이용 ->> replaceAll() : 모든 매칭을 치환
			word = word.replaceAll(",.\"\'`!?;:-()", "");  // 빈걸로 치환하면 삭제할 수 있다.


			if(word.length() >= 2){  // 단어길이가 2 이상인지 확인하기
				if(hmap.containsKey(word)) {  // Hashmap 에 그 단어가 있는지 확인하기
					hmap.put(word, hmap.get(word) + 1);  // 있다면 개수를 1더해준다
				}else{
					hmap.put(word, 1);  // 없다면 처음 발견시 1로 지정해준다
					}  // end else
			}  // end if
		}  //end for

		// 결과 출력

		// 내림차순으로 바꾸기 (앞서 했던걸 참고하면 LinkedList 로 변환해야한다.)
		List<Map.Entry<String, Integer>> list = new LinkedList<>(hmap.entrySet());

		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {

			@Override
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {  // 이러면 key 에 String(문자), Value 에 Integer(발생빈도)
				return o2.getValue() - o1.getValue();   // 내림차순 정렬
			}
		});

		int count = 0;
		for(Map.Entry<String, Integer> entry : list) {
			if(count >= 20) break;
			System.out.println(count + " " + entry.getKey() + " : " + entry.getValue());
		}
		
		System.out.println("\n프로그램 종료");
	} // end main()

} // end class




