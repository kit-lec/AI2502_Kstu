package practice.wordcount;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
 * 2] 빈도수(value) 내림차순으로 정렬하여 출력하기 (상위 20개만 출력)
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

		// 1차 작성
		/*
		{
			// 공백 단위로 나누어 배열로 저장
			String[] words = C.ALICE30.trim().toLowerCase().split("\\s+");

			// 특수문자를 모두 공백으로 변환
			String regex = "[0-9,.\"'`!?;:()-]";
			Pattern pattern = Pattern.compile(regex);
			for (int i = 0; i < words.length; i++) {
				Matcher matcher = pattern.matcher(words[i]);
				if (matcher.find()) {
					words[i] = matcher.replaceAll(" ");
				}
			}


			// 발생빈도 작성
			HashMap<String, Integer> hmap = new HashMap<>();

			for (int i = 0; i < words.length; i++) {
				String word = words[i];
				String[] wArr = word.split(" ");
				for (int j = 0; j < wArr.length; j++) {
					if (wArr[j].length() >= 2) {
						Integer count = hmap.getOrDefault(wArr[j], 0);
						hmap.put(wArr[j], count + 1);
					}
				}
			}
		}
		*/


		// 2차 작성 (반복문 줄인 버전)

		// 특수문자를 모두 공백으로 변환한 뒤, 공백 단위로 나누어 배열로 저장
		String book = C.ALICE30.trim().toLowerCase();

		Pattern pattern = Pattern.compile("[\\s+0-9,.\"'`!?;:()-]");
		Matcher matcher = pattern.matcher(book);
		if (matcher.find()) {
			book = matcher.replaceAll(" ");
		}

		String[] words = book.split(" ");


		// 발생 빈도 작성
		HashMap<String, Integer> hmap = new HashMap<>();

		for (int i = 0; i < words.length; i++) {
			String word = words[i];
			if (word.trim().length() >= 2) {
				Integer count = hmap.getOrDefault(word, 0);
				hmap.put(word, count + 1);
			}
		}


		// 결과 출력
		// 1. 전체 출력 (순서 X)
//		for (Map.Entry<String, Integer> entry : hmap.entrySet()) {
//			System.out.println(entry.getKey() + " : " + entry.getValue());
//		}

		// 2. 상위 20개 출력
		List<Map.Entry<String, Integer>> list = new ArrayList<>(hmap.entrySet());

		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				if (o1.getValue() > o2.getValue()) return -1;
				if (o1.getValue() < o2.getValue()) return 1;
				return 0;
			}
		});

		int cnt = 0;
		for (Map.Entry<String, Integer> entry : list) {
			if (cnt == 20) break;
			System.out.println(entry.getKey() + " : " + entry.getValue());
			cnt++;
		}


		System.out.println("\n프로그램 종료");
	} // end main()

} // end class

