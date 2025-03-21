-- ────────────────────────────────────────────────────────────
-- #01
-- 테이블 : t_customer
-- 질의사항:
--     여성 고객들의 point 합을 계산하세요.
-- 출력컬럼이름: [point합]
-- ↓ 쿼리를 작성하세요
-- 성별은 주민번호 7번째가 2인사람
select sum(c_point) point합
from t_customer
where substr(c_jumin, 7, 1) = '2';

# select * from t_customer;


-- #02
-- 테이블: t_customer
-- 질의사항:
--     point 가 500000 이상 700000 미만인 고객들의 평균나이를 계산하세요
--     나이는 '현재날짜' 기준으로 계산합니다
--     평균나이는 소숫점 1자리까지 출력
-- 출력컬럼: [평균나이]
-- ↓ 쿼리를 작성하세요
select round(avg(year(now()) - cast(concat('19', substr(c_jumin, 1, 2)) as signed ) + 1), 1) 평균나이
from t_customer
where c_point between 500000 and 700000;



-- #03
-- 테이블: t_emp, t_dept
-- 질의사항:
--   CHICAGO 와 DALLAS 에 근무하는 직원들의 숫자와 교통비(comm) 평균 을 계산해보세요.
--   교통비 평균은 반올림하여 표시
-- 출력컬럼: [지역명][직원수][교통비평균]
-- 정렬: 교통비평균 내림차순
-- 출력예시: https://docs.google.com/presentation/d/1yRKE20j6qwZBUjeOMoYzgNIBLJkGtzs6gQJNjQXKypQ/edit#slide=id.g14b31bc663c_0_88
-- ↓ 쿼리를 작성하세요
# select * from t_dept;
# select * from t_emp;
-- 그러면 deptno 가 20 또는 30 인 사람들이 두 지역에서 근무.
select d.LOC 지역명, count(*) 직원수, round(avg(ifnull(e.COMM, 0))) 교통비평균
    from t_emp e, t_dept d
where e.DEPTNO = d.DEPTNO and (e.DEPTNO = 20 or e.DEPTNO = 30)
group by e.DEPTNO
order by 교통비평균 desc;



-- #04
-- 테이블: t_sales, t_product
-- 질의사항: 상품별 로 총판매수량 과 판매금액합계 출력
--
-- 출력컬럼: [상품명][총판매수량][판매금액합계]
-- 정렬: 총판매수량 내림차순,
-- 출력예시: https://docs.google.com/presentation/d/1yRKE20j6qwZBUjeOMoYzgNIBLJkGtzs6gQJNjQXKypQ/edit#slide=id.g14b31bc663c_0_95
-- ↓ 쿼리를 작성하세요

# select * from t_sales;
# select * from t_product;

select p.p_name 상품명, sum(s.s_qty) 총판매수량, sum(s.s_total) 판매금액합계
    from t_sales s, t_product p
where s.s_code = p.p_code
group by s.s_code
order by 총판매수량 desc;
-- qty: 판매개수 total: 총액


-- #05
-- 테이블: t_student, t_professor, t_department
-- 질의사항:
--      '모든' 교수님 목록을 출력하려 합니다.
--
-- 출력컬럼 : [지도교수번호][지도교수이름][지도교수학과명][지도학생수]
-- 정렬: 지도학생수가 많은 순으로 (내림차순), 그리고 지도교수이름 순 (오름차순)
-- 출력예시: https://docs.google.com/presentation/d/1yRKE20j6qwZBUjeOMoYzgNIBLJkGtzs6gQJNjQXKypQ/edit#slide=id.g14b31bc663c_0_81
-- ↓ 쿼리를 작성하세요
# select * from t_professor;

-- 일단 교수별 지도 학생수: 지도교수 없는 학생 5명
# select profno, count(*)
# from t_student
# group by profno;

select p1.profno 지도교수번호, p1.name 지도교수이름, d.dname 지도교수학과명, ifnull(p2.지도학생수, 0) 지도학생수
    from t_professor p1 left outer join (select profno, count(*) 지도학생수
                                        from t_student
                                        group by profno) p2
on p1.PROFNO = p2.profno
join t_department d
on p1.DEPTNO = d.deptno
order by p2.지도학생수 desc, p1.name asc;


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

# select * from t_emp2;
# select * from t_dept2;

-- pdept로 찾아가자
# select dcode, area from t_dept2;

select a.area 지역명, count(*) 직원수, round(avg(year(now()) - year(e.BIRTHDAY) + 1), 1) 평균나이
    from (select dcode, area from t_dept2) a, t_emp2 e
where a.dcode = e.DEPTNO
group by a.area
order by 평균나이 desc;



-- #07
-- 테이블: t_student, t_department, t_exam01, t_credit
-- 질의사항:
--   학과별로 이번 시험 등급자 분포를 알아보고자 합니다
-- 출력컬럼: [학과명][등급][학생수]
-- 정렬: 학과명 오름차순, 등급 오름차순
-- 출력예시: https://docs.google.com/presentation/d/1yRKE20j6qwZBUjeOMoYzgNIBLJkGtzs6gQJNjQXKypQ/edit#slide=id.g14b31bc663c_0_57
-- ↓ 쿼리를 작성하세요
# select * from t_department;
# select * from t_exam01;
# select * from t_student;
#
# select s.studno, s.deptno1, e.total
#        from t_student s, t_exam01 e
# where s.studno = e.studno;

select d.dname 학과명, c.grade 등급, count(*) 학생수
    from t_department d, t_credit c, (select s.studno, s.deptno1, e.total
                                      from t_student s, t_exam01 e
                                      where s.studno = e.studno) s
    where s.deptno1 = d.deptno and s.total between c.min_point and c.max_point
group by d.dname, c.grade
order by d.dname, c.grade;























