-- ────────────────────────────────────────────────────────────
-- #01
-- 테이블 : t_customer
-- 질의사항:
--     여성 고객들의 point 합을 계산하세요.
-- 출력컬럼이름: [point합]
-- ↓ 쿼리를 작성하세요
select * from t_customer
SELECt SUM(c_point) 'point 합'
FROM t_customer
WHERE c_jumin LIKE '______2%' or '_______4%';



-- #02
-- 테이블: t_customer
-- 질의사항:
--     point 가 500000 이상 700000 미만인 고객들의 평균나이를 계산하세요
--     나이는 '현재날짜' 기준으로 계산합니다
--     평균나이는 소숫점 1자리까지 출력
-- 출력컬럼: [평균나이]
-- ↓ 쿼리를 작성하세요
select * from t_customer
select c_jumin from t_customer
where c_point between 500000 and 700000;


select timestampdiff(year, date_format(c_jumin, 'Y%'), now()) from t_customer
where c_jumin in (select c_jumin from t_customer
                  where c_point between 500000 and 700000);

-- #03
-- 테이블: t_emp, t_dept
-- 질의사항:
--   CHICAGO 와 DALLAS 에 근무하는 직원들의 숫자와 교통비(comm) 평균 을 계산해보세요.
--   교통비 평균은 반올림하여 표시
-- 출력컬럼: [지역명][직원수][교통비평균]
-- 정렬: 교통비평균 내림차순
-- 출력예시: https://docs.google.com/presentation/d/1yRKE20j6qwZBUjeOMoYzgNIBLJkGtzs6gQJNjQXKypQ/edit#slide=id.g14b31bc663c_0_88
-- ↓ 쿼리를 작성하세요

select * from t_dept d, t_emp e
select d.loc '지역', count(*) '직원수', round(avg(comm)) '교통비평균' from t_dept d, t_emp e
where d.DEPTNO = d.DEPTNO
group by d.loc;



-- #04
-- 테이블: t_sales, t_product
-- 질의사항: 상품별 로 총판매수량 과 판매금액합계 출력
--
-- 출력컬럼: [상품명][총판매수량][판매금액합계]
-- 정렬: 총판매수량 내림차순,
-- 출력예시: https://docs.google.com/presentation/d/1yRKE20j6qwZBUjeOMoYzgNIBLJkGtzs6gQJNjQXKypQ/edit#slide=id.g14b31bc663c_0_95
-- ↓ 쿼리를 작성하세요
select * from t_sales
select * from t_product

select p_name, count(p_name), sum(p_price) * count(p_name) from t_sales s, t_product p
where p_code = s_code
group by p_name;






-- #05
-- 테이블: t_student, t_professor, t_department
-- 질의사항:
--      '모든' 교수님 목록을 출력하려 합니다.
--
-- 출력컬럼 : [지도교수번호][지도교수이름][지도교수학과명][지도학생수]
-- 정렬: 지도학생수가 많은 순으로 (내림차순), 그리고 지도교수이름 순 (오름차순)
-- 출력예시: https://docs.google.com/presentation/d/1yRKE20j6qwZBUjeOMoYzgNIBLJkGtzs6gQJNjQXKypQ/edit#slide=id.g14b31bc663c_0_81
-- ↓ 쿼리를 작성하세요



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




-- #07
-- 테이블: t_student, t_department, t_exam01, t_credit
-- 질의사항:
--   학과별로 이번 시험 등급자 분포를 알아보고자 합니다
-- 출력컬럼: [학과명][등급][학생수]
-- 정렬: 학과명 오름차순, 등급 오름차순
-- 출력예시: https://docs.google.com/presentation/d/1yRKE20j6qwZBUjeOMoYzgNIBLJkGtzs6gQJNjQXKypQ/edit#slide=id.g14b31bc663c_0_57
-- ↓ 쿼리를 작성하세요


























