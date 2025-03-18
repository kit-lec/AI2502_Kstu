package practice.wordcount;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.StringTokenizer;

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
 * 2] 빈도수 내림차순으로 정렬하여 출력하기
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
		
		String [] words = C.ALICE30.trim().toLowerCase().split("\\s+");

		// 발생빈도 작성
		for (String word : words) {
			word = word.replaceAll(",.\"\'`!?;:-()", "");  //  구두점/기호 잘라내기
			if (word.length() >= 2) {   // 2글자 이상만 계수
				hmap.put(word, hmap.getOrDefault(word, 0) + 1);  // 단어 빈도수를 1씩 더하기
			}
		}

		LinkedList<Map.Entry<String, Integer>> wordList = new LinkedList(hmap.entrySet());  // 정렬 가능하도록 변환
		wordList.sort((a, b) -> b.getValue().compareTo(a.getValue()));  // 내림차순 정렬
		
		// 결과 출력
		final int[] rank = {1};  // rank를 이용한 단어 빈도수 1위 출력
		wordList.forEach(entry -> {
					System.out.println(rank[0] + ": " + entry.getKey() + " : " + entry.getValue() + "개");
					rank[0]++;  // 순위 1씩 증가
				});
		
		System.out.println("\n프로그램 종료");
	} // end main()

} // end class




