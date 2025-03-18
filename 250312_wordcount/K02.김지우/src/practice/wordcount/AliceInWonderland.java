package practice.wordcount;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 1] 문서(문자열) 안의 단어의 빈도수를 계수해서 출력하기
 * 	- 대소문자 구분하지 않기 :   The 와 the 는 같은 단어
 *  - 2글자 이상만 계수하기
 *  - 구두점/기호 ",.\"\'`!?;:-()" 잘라내기 (공백으로 치환 후 단어로 쪼개기)
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
 * 2] 빈도수 내림차순으로 정렬하여 출력하기 (상위 20개만)
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

		String [] words = C.ALICE30.replaceAll("[^a-zA-Z0-9]+", " ").trim().toLowerCase().split("\\s+");

//		for (int i = 0; i < words.length; i++) {
//			System.out.print(words[i] + " ");
//		} // 잘 찍히나 확인하는 용도였음

		// 발생빈도 작성
		for (int i = 0; i < words.length; i++) {

			Integer count = hmap.get(words[i]);

			if (count == null) {
				hmap.put(words[i], 1);
			}
			else{
				hmap.put(words[i], count + 1);
			}
		}

		List<Map.Entry<String, Integer>> list = new ArrayList<>(hmap.entrySet());
		Collections.sort(list, new Desc());
//		System.out.println(list);

		for (int i = 0; i < 20; i++) {
			Map.Entry<String, Integer> entry = list.get(i);
			System.out.println((i + 1) + " " + entry.getKey() + " : " + entry.getValue() + "개");
		}

		System.out.println("\n프로그램 종료");
	} // end main()

} // end class


class Desc implements Comparator<Map.Entry<String, Integer>> {
	@Override
	public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
		if (o1.getValue() > o2.getValue()) return -1;
		if (o1.getValue() < o2.getValue()) return 1;
		return 0;
	}
}

