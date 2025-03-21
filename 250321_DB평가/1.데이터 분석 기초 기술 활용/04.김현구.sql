-- ────────────────────────────────────────────────────────────
-- #01
-- 테이블 : t_customer
-- 질의사항:
--     여성 고객들의 point 합을 계산하세요.
-- 출력컬럼이름: [point합]
-- ↓ 쿼리를 작성하세요
select *
from t_customer;


select sum(c_point) point합
from t_customer
where substring(c_jumin, 7, 1) = 2
   or substring(c_jumin, 7, 1) = 4;

-- #02
-- 테이블: t_customer
-- 질의사항:
--     point 가 500000 이상 700000 미만인 고객들의 평균나이를 계산하세요
--     나이는 '현재날짜' 기준으로 계산합니다
--     평균나이는 소숫점 1자리까지 출력
-- 출력컬럼: [평균나이]
-- ↓ 쿼리를 작성하세요
-- 2번 못풀음...
select avg(timestampdiff(year, substring(c_jumin, 6, 1), now()))
from t_customer
where c_point >= 500000
  and c_point < 700000;

select substring(curdate(), 1, 4);
select substring(c_jumin, 1, 2)
from t_customer;
-- #03
-- 테이블: t_emp, t_dept
-- 질의사항:
--   CHICAGO 와 DALLAS 에 근무하는 직원들의 숫자와 교통비(comm) 평균 을 계산해보세요.
--   교통비 평균은 반올림하여 표시
-- 출력컬럼: [지역명][직원수][교통비평균]
-- 정렬: 교통비평균 내림차순
-- 출력예시: https://docs.google.com/presentation/d/1yRKE20j6qwZBUjeOMoYzgNIBLJkGtzs6gQJNjQXKypQ/edit#slide=id.g14b31bc663c_0_88
-- ↓ 쿼리를 작성하세요
select *
from t_emp;
select *
from t_dept;


select d.LOC 지역명, count(*) 직원수, format(avg(ifnull(comm, 0)), 0) 교통비평균
from t_emp e,
     t_dept d
where d.DEPTNO = e.DEPTNO
  and (loc = 'CHICAGO' or loc = 'DALLAS')
group by d.LOC
order by 교통비평균 desc;


-- #04
-- 테이블: t_sales, t_product
-- 질의사항: 상품별 로 총판매수량 과 판매금액합계 출력
--
-- 출력컬럼: [상품명][총판매수량][판매금액합계]
-- 정렬: 총판매수량 내림차순,
-- 출력예시: https://docs.google.com/presentation/d/1yRKE20j6qwZBUjeOMoYzgNIBLJkGtzs6gQJNjQXKypQ/edit#slide=id.g14b31bc663c_0_95
-- ↓ 쿼리를 작성하세요
select *
from t_sales;
select *
from t_product;

select p.p_name 상품명, sum(s.s_qty) 총판매수량, sum(s.s_total) 판매금액합계
from t_sales s,
     t_product p
where s.s_code = p.p_code
group by p.p_name
order by sum(s.s_qty) desc;


-- #05
-- 테이블: t_student, t_professor, t_department
-- 질의사항:
--      '모든' 교수님 목록을 출력하려 합니다.
--
-- 출력컬럼 : [지도교수번호][지도교수이름][지도교수학과명][지도학생수]
-- 정렬: 지도학생수가 많은 순으로 (내림차순), 그리고 지도교수이름 순 (오름차순)
-- 출력예시: https://docs.google.com/presentation/d/1yRKE20j6qwZBUjeOMoYzgNIBLJkGtzs6gQJNjQXKypQ/edit#slide=id.g14b31bc663c_0_81
-- ↓ 쿼리를 작성하세요
select *
from t_student;
select *
from t_professor;
select *
from t_department;

select p.PROFNO 지도교수번호, p.name 지도교수이름, d.dname 지도교수학과명, ifnull(count(*), 0) 지도학생수
from t_professor p,
     t_student s,
     t_department d
where d.deptno = p.DEPTNO
  and s.profno = p.PROFNO
group by p.PROFNO, p.name, d.dname
order by ifnull(count(*), 0) desc, p.name asc;

select p.PROFNO 지도교수번호, p.name 지도교수이름, d.dname 지도교수학과명, ifnull(count(s.studno), 0) 지도학생수
from t_professor p
         left join t_student s on s.profno = p.PROFNO
         join t_department d on d.deptno = p.DEPTNO
group by p.PROFNO, p.name, d.dname
order by ifnull(count(s.studno), 0) desc, p.name asc;



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
select *
from t_emp2;
select *
from t_dept2;

select d.area 지역명, count(e.EMPNO) 직원수, format(avg(timestampdiff(year, e.BIRTHDAY, now())), 1) 평균나이
from t_emp2 e,
     t_dept2 d
where e.DEPTNO = d.DCODE
group by d.area
order by 평균나이 desc;

-- #07
-- 테이블: t_student, t_department, t_exam01, t_credit
-- 질의사항:
--   학과별로 이번 시험 등급자 분포를 알아보고자 합니다
-- 출력컬럼: [학과명][등급][학생수]
-- 정렬: 학과명 오름차순, 등급 오름차순
-- 출력예시: https://docs.google.com/presentation/d/1yRKE20j6qwZBUjeOMoYzgNIBLJkGtzs6gQJNjQXKypQ/edit#slide=id.g14b31bc663c_0_57
-- ↓ 쿼리를 작성하세요
select *
from t_student;
select *
from t_department;
select *
from t_exam01;
select *
from t_credit;

select d.dname 학과명, c.grade 등급, count(s.studno) 학생수
from t_student s,
     t_department d,
     t_exam01 e,
     t_credit c
where d.deptno = s.deptno1
  and e.studno = s.studno
  and (e.total between c.min_point and c.max_point)
group by d.dname, c.grade
order by 학과명 asc, 등급 asc;

























