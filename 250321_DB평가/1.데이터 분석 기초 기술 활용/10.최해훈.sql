-- ────────────────────────────────────────────────────────────
-- #01
-- 테이블 : t_customer
-- 질의사항:
--     여성 고객들의 point 합을 계산하세요.
-- 출력컬럼이름: [point합]
-- ↓ 쿼리를 작성하세요
SELECT * FROM t_customer;
SELECT sum(c_point) "point합" FROM t_customer WHERE substr(c_jumin, 7, 1) = '2';



-- #02
-- 테이블: t_customer
-- 질의사항:
--     point 가 500000 이상 700000 미만인 고객들의 평균나이를 계산하세요
--     나이는 '현재날짜' 기준으로 계산합니다
--     평균나이는 소숫점 1자리까지 출력
-- 출력컬럼: [평균나이]
-- ↓ 쿼리를 작성하세요
SELECT round(avg(timestampdiff(year, date_format(substr(c_jumin,1,6), '%Y-%m-%d'), now())), 1) "평균나이" FROM t_customer WHERE c_point BETWEEN 500000 AND 699999;




-- #03
-- 테이블: t_emp, t_dept
-- 질의사항:
--   CHICAGO 와 DALLAS 에 근무하는 직원들의 숫자와 교통비(comm) 평균 을 계산해보세요.
--   교통비 평균은 반올림하여 표시
-- 출력컬럼: [지역명][직원수][교통비평균]
-- 정렬: 교통비평균 내림차순
-- 출력예시: https://docs.google.com/presentation/d/1yRKE20j6qwZBUjeOMoYzgNIBLJkGtzs6gQJNjQXKypQ/edit#slide=id.g14b31bc663c_0_88
-- ↓ 쿼리를 작성하세요
SELECT d.LOC "지역명", count(*) "직원수", round(avg(if(isnull(e.COMM), 0, e.COMM)), 0) "교통비평균"
FROM t_emp e, t_dept d
WHERE e.deptno = d.DEPTNO AND d.LOC IN ('CHICAGO', 'DALLAS')
GROUP BY d.LOC
ORDER BY 3 DESC;


-- #04
-- 테이블: t_sales, t_product
-- 질의사항: 상품별 로 총판매수량 과 판매금액합계 출력
--
-- 출력컬럼: [상품명][총판매수량][판매금액합계]
-- 정렬: 총판매수량 내림차순,
-- 출력예시: https://docs.google.com/presentation/d/1yRKE20j6qwZBUjeOMoYzgNIBLJkGtzs6gQJNjQXKypQ/edit#slide=id.g14b31bc663c_0_95
-- ↓ 쿼리를 작성하세요
SELECT p.p_name "상품명", sum(s.s_qty) "총판매수량", sum(s.s_total) "판매금액합계"
FROM t_sales s, t_product p
WHERE s.s_code = p.p_code
GROUP BY p.p_name
ORDER BY 2 DESC;




-- #05
-- 테이블: t_student, t_professor, t_department
-- 질의사항:
--      '모든' 교수님 목록을 출력하려 합니다.
--
-- 출력컬럼 : [지도교수번호][지도교수이름][지도교수학과명][지도학생수]
-- 정렬: 지도학생수가 많은 순으로 (내림차순), 그리고 지도교수이름 순 (오름차순)
-- 출력예시: https://docs.google.com/presentation/d/1yRKE20j6qwZBUjeOMoYzgNIBLJkGtzs6gQJNjQXKypQ/edit#slide=id.g14b31bc663c_0_81
-- ↓ 쿼리를 작성하세요
SELECT * FROM t_professor;
SELECT * FROM t_department;
SELECT * FROM t_student;

SELECT if(isnull(s.profno), 0, s.profno) FROM t_student s;
SELECT count(if(isnull(s.profno), 0, s.profno)) FROM t_student s, t_professor p WHERE s.profno = p.profno GROUP BY p.profno;


SELECT p.profno "지도교수번호", p.name "지도교수이름", d.dname "지도교수학과명", if(isnull(count(if(isnull(s.profno), 0, s.profno))), 0, count(if(isnull(s.profno), 0, s.profno))) "지도학생수"
FROM t_student s, t_professor p, t_department d
WHERE p.deptno = d.deptno AND s.profno = p.profno
GROUP BY p.profno, p.name, d.dname
ORDER BY 4 DESC
;


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
SELECT d.area "지역명", count(*) "직원수", round(avg(timestampdiff(year, e.birthday, now())), 1) "평균나이"
FROM t_emp2 e, t_dept2 d
WHERE d.dcode = e.deptno
GROUP BY d.area
ORDER BY 3 DESC
;


-- #07
-- 테이블: t_student, t_department, t_exam01, t_credit
-- 질의사항:
--   학과별로 이번 시험 등급자 분포를 알아보고자 합니다
-- 출력컬럼: [학과명][등급][학생수]
-- 정렬: 학과명 오름차순, 등급 오름차순
-- 출력예시: https://docs.google.com/presentation/d/1yRKE20j6qwZBUjeOMoYzgNIBLJkGtzs6gQJNjQXKypQ/edit#slide=id.g14b31bc663c_0_57
-- ↓ 쿼리를 작성하세요
SELECT * FROM t_student;
SELECT * FROM t_credit;
SELECT * FROM t_exam01;
SELECT * FROM t_department;
-- , count(s.name) "학생수"

SELECT d.dname "학과명", c.grade "등급" FROM t_student s, t_department d, t_exam01 e, t_credit c
WHERE s.deptno1 = d.deptno AND (e.total BETWEEN c.min_point AND c.max_point)
GROUP BY d.dname, c.grade
;

























