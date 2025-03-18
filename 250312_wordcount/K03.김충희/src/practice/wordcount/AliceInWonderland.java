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
		//1, 단어에 2글자 이상인 것만 출력한다.
		//2, 단어 안에서 특수 기호를 제거한다.
		// 3, 제거 후에 같은 단어들을 조합한다.
		// 4, hashmap을 이용해 같은 단어의 개수를 센다.
		// 5, 상위 20 개의 단어만 추출한다.
		String[] words = C.ALICE30.trim().toLowerCase().split("\\s+");
		System.out.println(Arrays.toString(words));


		// 발생빈도 작성
		HashMap<String, Integer> hash = new HashMap<>();
		// 특수문자 제거
		for (int i = 0; i < words.length - 1; i++) {
			String word = words[i];
			if (word.length() >= 2) {
				word = word.replaceAll("[\",.\\\"'`!?;:\\-()\\[\\]]", "");
				hash.put(word, hash.getOrDefault(word, 0) + 1);


			}
		}
		//단어 갯수 출력
		for (Map.Entry<String, Integer> entry : hash.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
		List<WordCoount> wordCounts = new ArrayList<>();
		for (Map.Entry<String, Integer> entry : hash.entrySet()) {
			wordCounts.add(new WordCoount(entry.getKey(), entry.getValue()));
		}

		// 내림차순 정렬
		Collections.sort(wordCounts);

		System.out.println("상위 20개 단어");
		for (int i = 0; i < Math.min(20, wordCounts.size()); i++) {
			WordCoount wc = wordCounts.get(i);
			System.out.println(wc.word + " : " + wc.count);
		}
	}
}


		// 특수 기호하고 정렬한 하면 된다




		
		// 결과 출력
		// TODO
		

 // end main()

// end class
class WordCoount implements Comparable<WordCoount> {
	String word;
	int count;

	@Override
	public int compareTo(WordCoount o) {
		return o.count - this.count;
	}
	public WordCoount(String word, int count) {
		this.word = word;
		this.count = count;
	}
}




