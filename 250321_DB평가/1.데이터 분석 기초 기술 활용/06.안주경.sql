-- ────────────────────────────────────────────────────────────
-- #01
-- 테이블 : t_customer
-- 질의사항:
--     여성 고객들의 point 합을 계산하세요.
-- 출력컬럼이름: [point합]
-- ↓ 쿼리를 작성하세요

select sum(c_point) point합
from t_customer
where c_jumin like '______2%';

-- #02
-- 테이블: t_customer
-- 질의사항:
--     point 가 500000 이상 700000 미만인 고객들의 평균나이를 계산하세요
--     나이는 '현재날짜' 기준으로 계산합니다
--     평균나이는 소숫점 1자리까지 출력
-- 출력컬럼: [평균나이]
-- ↓ 쿼리를 작성하세요
SELECT round(AVG(TIMESTAMPDIFF(YEAR, STR_TO_DATE(CONCAT('19', SUBSTRING(c_jumin, 1, 6)), '%Y%m%d'), CURDATE()) + 1),
             1) 평균나이
FROM t_customer
WHERE c_point BETWEEN 500000 AND 700000;


-- #03
-- 테이블: t_emp, t_dept
-- 질의사항:
--   CHICAGO 와 DALLAS 에 근무하는 직원들의 숫자와 교통비(comm) 평균 을 계산해보세요.
--   교통비 평균은 반올림하여 표시
-- 출력컬럼: [지역명][직원수][교통비평균]
-- 정렬: 교통비평균 내림차순
-- 출력예시: https://docs.google.com/presentation/d/1yRKE20j6qwZBUjeOMoYzgNIBLJkGtzs6gQJNjQXKypQ/edit#slide=id.g14b31bc663c_0_88
-- ↓ 쿼리를 작성하세요
SELECT d.LOC                               지역명,
       COUNT(e.EMPNO)                      직원수,
       ROUND(AVG(IFNULL(e.COMM, 0)), 0) AS 교통비평균
FROM t_emp e,
     t_dept d
WHERE e.DEPTNO = d.DEPTNO
  AND d.LOC IN ('CHICAGO', 'DALLAS')
GROUP BY d.LOC
ORDER BY 교통비평균 DESC;


-- #04
-- 테이블: t_sales, t_product
-- 질의사항: 상품별 로 총판매수량 과 판매금액합계 출력
--
-- 출력컬럼: [상품명][총판매수량][판매금액합계]
-- 정렬: 총판매수량 내림차순,
-- 출력예시: https://docs.google.com/presentation/d/1yRKE20j6qwZBUjeOMoYzgNIBLJkGtzs6gQJNjQXKypQ/edit#slide=id.g14b31bc663c_0_95
-- ↓ 쿼리를 작성하세요
SELECT p.p_name       상품명,
       SUM(s.s_qty)   총판매수량,
       SUM(s.s_total) 판매금액합계
FROM t_sales s,
     t_product p
where s.s_code = p.p_code
GROUP BY p.p_name
ORDER BY 총판매수량 DESC;


-- #05
-- 테이블: t_student, t_professor, t_department
-- 질의사항:
--      '모든' 교수님 목록을 출력하려 합니다. >> ** LEFT JOIN
--
-- 출력컬럼 : [지도교수번호][지도교수이름][지도교수학과명][지도학생수]
-- 정렬: 지도학생수가 많은 순으로 (내림차순), 그리고 지도교수이름 순 (오름차순)
-- 출력예시: https://docs.google.com/presentation/d/1yRKE20j6qwZBUjeOMoYzgNIBLJkGtzs6gQJNjQXKypQ/edit#slide=id.g14b31bc663c_0_81
-- ↓ 쿼리를 작성하세요
SELECT p.profno        지도교수번호,
       p.name          지도교수이름,
       d.dname         지도교수학과명,
       COUNT(s.studno) 지도학생수
FROM t_professor p
         LEFT JOIN t_department d ON p.deptno = d.deptno
         LEFT JOIN t_student s ON p.profno = s.profno
GROUP BY p.profno, p.name, d.dname -- 지도교수 기준으로 group.
ORDER BY 지도학생수 DESC, 지도교수이름 ASC;

-- #06
-- 테이블: t_emp2, t_dept2
-- 질의사항:
--     지역(AREA)별로 근무하는 직원들의 직원수와 직원들의 평균나이를 계산하세요
--     나이는 '현재날짜' 기준으로 계산합니다
--     평균나이는 소숫점 1자리까지 출력
-- 출력컬럼: [지역명][직원수][평균나이]
-- 정렬: 평균나이 내림차순
-- 출력예시: https://docs.google.com/presentation/d/1yRKE20j6qwZBUjeOMoYzgNIBLJkGtzs6gQJNjQXKypQ/edit#slide=id.g14b31bc663c_0_74
-- ↓ 쿼리를 작성하세요
SELECT d.AREA                                                        지역명,
       COUNT(e.EMPNO)                                                직원수,
       ROUND(AVG(TIMESTAMPDIFF(YEAR, e.BIRTHDAY, CURDATE()) + 1), 1) 평균나이
FROM t_emp2 e,
     t_dept2 d
where e.DEPTNO = d.DCODE
GROUP BY d.AREA
ORDER BY 평균나이 DESC;


-- #07
-- 테이블: t_student, t_department, t_exam01, t_credit
-- 질의사항:
--   학과별로 이번 시험 등급자 분포를 알아보고자 합니다
-- 출력컬럼: [학과명][등급][학생수]
-- 정렬: 학과명 오름차순, 등급 오름차순
-- 출력예시: https://docs.google.com/presentation/d/1yRKE20j6qwZBUjeOMoYzgNIBLJkGtzs6gQJNjQXKypQ/edit#slide=id.g14b31bc663c_0_57
-- ↓ 쿼리를 작성하세요

SELECT d.dname          학과명,
       c.grade          등급,
       COUNT(s.studno)  학생수
FROM t_student s,
     t_department d,
     t_exam01 e,
     t_credit c
WHERE s.deptno1 = d.deptno
  AND s.studno = e.studno
  AND e.total BETWEEN c.min_point AND c.max_point
GROUP BY d.dname, c.grade
ORDER BY d.dname, c.grade;


























