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
 * 2] 빈도수 내림차순으로 정렬하여 출력하기 // 제일 많이 등장한 것부터 상위 20개만 출력
 * 	ex)
 *	1 the:113개
 *	2 she:95개
 *	3 to:85개   >>>value 기준 정렬
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

		// 정규표현식으로 단어만 추출 (구두점 제거)
		String[] words = C.ALICE30.toLowerCase().replaceAll("[^a-zA-Z ]", "").split("\\s+");

		// 단어의 빈도수 계수 (2글자 이상)
		for (String word : words) {
			if (word.length() >= 2) {
				hmap.put(word, hmap.getOrDefault(word, 0) + 1);
			}
		}
		
		// 결과 출력
		// 빈도수 내림차순으로 정렬
		List<Map.Entry<String, Integer>> list = new LinkedList<>(hmap.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});

		// 상위 20개 출력
		System.out.println("\n상위 20개의 단어 빈도수:");
		int count = 0;
		for (Map.Entry<String, Integer> entry : list) {
			System.out.println((count + 1) + ". " + entry.getKey() + ": " + entry.getValue() + "개");
			count++;
			if (count >= 20) break;
		}
		
		System.out.println("\n프로그램 종료");
	} // end main()

} // end class




