package com.J03.클래스의List;

import dataset.DataSet;
import dataset.Score;
import dataset.Student;

import java.util.ArrayList;
import java.util.List;

public class Collection03Main {

	public static void main(String[] args) {
		System.out.println("List 연습");

		// Student를 저장할 수 있는 ArrayList 인스턴스 생성
		List<Student> students = DataSet.students;

		for(var e : students) System.out.println(e);  // 일단 출력

		//------------------------------------------------------------
		// 김동혁 학생의 정보 수정 (김동혁 학생이 List의 몇번째 저장되었는지 모른다고 하면?)
		// id 값 -> "D002"
		// 국어, 영어, 수학 -> 99, 88, 76
		// 출력예시]
		//  수정전: Student{id='D001', name='김동혁', grade=3, major='Design', score=[국: 80,영:100,수: 90]}
		//  수정후: Student{id='D002', name='김동혁', grade=3, major='Design', score=[국: 99,영: 88,수: 76]}
		System.out.println("\n학생 정보 수정");
		{
			// TODO  set
			for (Student s : students) {
				if (s.getId().equals("김동혁")){
					s.setId("D002");
					s.setScore(new Score(99, 88, 76));
					System.out.println(s);
				}
			}




		}

		//------------------------------------------------------------
		// 1학년 과 3학년 출력하세요
		// 출력예시]
		//		Student{id='S001', name='김남승', grade=3, major='Software', score=[국:100,영: 90,수: 80]}
		//		Student{id='D002', name='김동혁', grade=3, major='Design', score=[국: 99,영: 88,수: 76]}
		//		Student{id='S006', name='Clint', grade=3, major='Software', score=[국: 99,영: 80,수: 21]}
		//		Student{id='T001', name='Peter', grade=1, major='Theater', score=[국: 59,영: 88,수: 21]}
		System.out.println("\n1학년, 3학년 학생 출력");
		{
			for (Student s : students) {
				if (s.getGrade() == 3 ){
					System.out.println(s);
				}
			}
		}

		//------------------------------------------------------------
		// 3학년 이상의 학생이 몇명인지 출력하세요
		//  출력예시]
		// 		6명
		System.out.println("\n3학년 이상 학생수");
		{
			int count = 0;
			for (Student s : students) {
				if (s.getGrade() > 3 ){
					count++;
				}
			}
				System.out.println(count);
		}

		//------------------------------------------------------------
		// 각 전공별로 학생이 몇명인지 출력해보세요
		// 출력예시]
		// 	SoftWare: 6
		// 	Design: 2
		// 	Theater: 1
		// 	Advertisement: 1
		System.out.println("\n전공별 학생수");
		{
			int sf = 0;
			int dn = 0;
			int ad = 0;
			int th = 0;
			for (Student s : students) {
				if (s.getMajor().equals("SoftWare")){
					sf++;
				}
				else if (s.getMajor().equals("Design")){
					dn++;
				}
				else if (s.getMajor().equals("Theater")) {
					th++;
				}
				else if (s.getMajor().equals("Advertisement")){
					ad++;
				}

			}
			System.out.println("SoftWare: " + sf + " Design: " + dn + " Advertisement: " + ad + " Theater: " + th);
		}

		// 평균의 최댓값, 최솟값 을 출력해보세요
		//
		// [출력예시]
		// 	Max: 99.0
		// 	Min: 43.7
		System.out.println("\n평균 의 최댓값, 최솟값");
		{   int sum = 0;
			List<Score> scores = new ArrayList<>();
			System.out.println(scores.get(0));

		}

		System.out.println("\n프로그램 종료");
	} // end main()

} // end class









