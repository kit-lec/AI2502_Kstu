package practice.wordcount;

import java.util.*;

import static java.lang.Integer.parseInt;

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
       public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("실습: 단어 발생 빈도");


//        <본문 정제 작업>
//        (설명)
//        - String[] words
//          > 개체 : "words" 생성.
//          > 형식 : String 형 1차원 배열
//        - C (C.java) : 원문 전문.
//        - .trim() : 불필요한 공백문자 제거.
//        - .toLowerCase() : 소문자로 변환.
//        - .replaceAll("[...]", "") : [...] 안의 특수문자를 공백문자 ""으로 대체.
//        - .split("\\s+") : 공백문자(\\s+, +는 공백문자 다수)를 기준으로 원문 String을 분할 후 array에 단어별로 저장.
//        (결과)
//        C.java 원문에서 불필요한 공백문자 및 특수문자를 제거하고, 소문자로 대체함.
//        String[] words 에 공백문자(\\s+) 기준으로 분할된 String 들이 원소(= word)로 저장.
//
//        String [] words = C.ALICE30.trim().toLowerCase().split("\\s+");
        String[] words = C.ALICE30.trim().toLowerCase().replaceAll("[\"*,.\\'`!?;:\\-()]", "").split("\\s+");

        // [발생빈도 작성]
//        (설명)
//        <새 HashMap 개체 생성>
//        - HashMap<String, Integer> wMap :
//          > 개체 wMap 생성.
//          > 형식 : new HashMap<>()
//          > wMap 은 <Key : String, Value : Integer>인 HashMap<>().

        HashMap<String, Integer> wMap = new HashMap<>();

        // (설명)
        // 2글자 이상 (1글자 초과) 각 단어 (key) 마다 for 문을 돌리고, 등장할 때 마다 wMap 에 value 값을 1씩 추가.
        // 처음 등장하면 (0회)의 경우 기본값 0에 1 추가
        for (String word : words) {
            if (word.length() > 1) {
                wMap.put(word, wMap.getOrDefault(word, 0) + 1);
            }
        }

        // (설명)
        // HashMap<>() 타입 개체 wMap 을 List<> 타입 개체 wList 로 형변환 <= 정렬(sort)을 가능하게 하기 위해서
        List<Map.Entry<String, Integer>> wList = new ArrayList<>(wMap.entrySet());

        // [단어 정렬 및 분류]
        wList.sort((o1, o2) -> o2.getValue() - o1.getValue());

        // [결과 출력]
        // (설명)
        // - int rank;
        // - Scanner 개체 등을 활용해 rank 값을 입력받을 수 있음.
        // rank 값이 전체 단어 개수를 초과하면 "기본값 20"으로 설정.

        System.out.println("[WORDS]");
        System.out.println("몇 등 까지 보시겠습니까?");
        int rank = sc.nextInt();
        sc.nextLine();
        if (rank >= wList.size()) { rank = 20; };



        // (설명)
        // int rank 범위 내에서 결과 출력
        for (int i = 0; i < rank; i++) {
            System.out.println((i + 1) + "." + wList.get(i).getKey() + ":" + wList.get(i).getValue());
        }



        System.out.println("\n프로그램 종료");
    } // end main()
} // end class;










