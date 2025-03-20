-- #7109) 연습
-- t_student 테이블 :
-- 전체학생중에서 체중이 4학년 학생들의 체중에서
-- 가장 적게 나가는 학생보다
-- 몸무게가 적은 학생의 이름과 학년과 몸무게를 출력하세요
select name, grade, weight
from t_student
where weight < (select min(weight) from t_student where grade = 4);

-- #7110) 연습
-- t_emp2, t_dept2 테이블 :
-- 각 부서별 평균 연봉을 구하고 그 중에서 평균 연봉이 가장 적은 부서의 평균연봉보다
-- 적게 받는 직원들의 부서명, 직원명, 연봉을 출력 하세요
-- TODO

SELECT d.DNAME 부서명, e.NAME 직원명, e.PAY 연봉
FROM t_emp2 e,
     t_dept2 d
WHERE e.DEPTNO = d.DCODE
  AND e.PAY < (
    -- 1. 각 부서별 평균 연봉 구하고 가장 낮은 평균연봉 찾기
    SELECT MIN(
                   (SELECT AVG(PAY) -- 2. 부서별 평균연봉 구하기
                    FROM t_emp2
                    WHERE DEPTNO = a.DEPTNO) -- 현재 조사중인 deptno 별로 평균연봉 구함
        -- a.DEPTNO는 바깥 쿼리에서 가져온 t_emp2의 모든 부서 의미
        -- 즉 t_emp2의 모든 행에 대해 해당 부서 평균연봉 구하는 서브쿼리실행
           )
    FROM t_emp2 a -- 3. t_emp2 테이블 한번 더 조회하여 모든 부서 대상 서브쿼리 실행
    -- t_emp2를 다시 조회하며 모든 deptno에 대해 부서별 평균 연봉 구한 뒤
    -- 그 중 가장 낮은 평균연봉 MIN()으로 선택
);

-- #7202) 연습
-- t_professor , t_department 테이블 :
-- 각 학과별로 입사일이 가장 오래된 교수의
-- 교수번호와 이름, 입사일, 학과명을 출력하세요.
-- 단 학과이름 순으로 오름차순 정렬하세요
select *
from t_department;
select *
from t_professor;
select p.PROFNO 교수번호, p.name 교수명, p.HIREDATE 입사일, d.dname 학과명
from t_professor p,
     t_department d
WHERE p.DEPTNO = d.deptno
  AND p.HIREDATE = (SELECT MIN(HIREDATE)
                    FROM t_professor
                    WHERE DEPTNO = p.DEPTNO)
ORDER BY d.dname;


-- #7203) 연습
-- t_emp2 테이블 :
-- 직급별로 해당직급에서 최대 연봉을 받는 직원의
-- 이름과 직급, 연봉을 출력하세요,
-- 단, 연봉순으로 오름차순 정렬하세요

select e.name 이름, e.post 직급, e.pay 연봉
from t_emp2 e
WHERE e.pay = (SELECT max(pay)
               from t_emp2
               WHERE post = e.post)
order by pay;


-- #7207) 예제
-- t_student, t_department 테이블 사용
-- 학생이름, 아이디, 학년, 제1전공 이름, 제2전공 이름이 나오게 하세요 (
-- 출력되게 하세요
-- ※ 스칼라 서브쿼리 활용.
select s.name,s.id,s.grade,
(select dname from t_department where deptno = s.deptno1) 제1전공,
ifnull((select dname from t_department where deptno = s.deptno2),'') 제2전공
from t_student s;