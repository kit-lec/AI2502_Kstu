-- #7109) 연습
-- t_student 테이블 :
-- 전체학생중에서 체중이 4학년 학생들의 체중에서
-- 가장 적게 나가는 학생보다
-- 몸무게가 적은 학생의 이름과 학년과 몸무게를 출력하세요

select min(weight)
from t_student
where grade = '4'
;

select name 이름, grade 학년, weight 몸무게
from t_student
where weight < (select min(weight)
                from t_student
                where grade = '4');


-- #7110) 연습
-- t_emp2, t_dept2 테이블 :
-- 각 부서별 평균 연봉을 구하고 그 중에서 평균 연봉이 가장 적은 부서의 평균연봉보다
-- 적게 받는 직원들의 부서명, 직원명, 연봉을 출력 하세요

select *
from t_emp2;
select *
from t_dept2;

select avg(pay)
from t_emp2
group by DEPTNO;

select d.dname 부서명, e.name 직원명, e.PAY 연봉
from t_emp2 e,
     t_dept2 d
where e.DEPTNO = d.DCODE
  and pay < all (select avg(pay)
                 from t_emp2
                 group by DEPTNO);

-- #7202) 연습
-- t_professor , t_department 테이블 :
-- 각 학과별로 입사일이 가장 오래된 교수의
-- 교수번호와 이름, 입사일, 학과명을 출력하세요.
-- 단 학과이름 순으로 오름차순 정렬하세요
select *
from t_professor;
select *
from t_department;

select deptno, min(HIREDATE)
from t_professor
group by DEPTNO;

select p.profno 교수번호, p.name 교수명, p.HIREDATE 입사일, d.dname 학과명
from t_professor p,
     t_department d
where d.deptno = p.deptno
  and (d.deptno, HIREDATE) in (select deptno, min(HIREDATE)
                               from t_professor
                               group by DEPTNO)
order by d.dname;



-- #7203) 연습
-- t_emp2 테이블 :
-- 직급별로 해당직급에서 최대 연봉을 받는 직원의
-- 이름과 직급, 연봉을 출력하세요,
-- 단, 연봉순으로 오름차순 정렬하세요
select *
from t_emp2;

select post, max(pay)
from t_emp2
group by post;

SELECT t1.name 사원명, t1.post 직급, t1.pay 연봉
FROM t_emp2 t1
         INNER JOIN (SELECT post, MAX(pay) AS max_pay
                     FROM t_emp2
                     GROUP BY post) t2
                    ON t1.post = t2.post AND t1.pay = t2.max_pay
ORDER BY t1.pay;


-- #7207) 예제
-- t_student, t_department 테이블 사용
-- 학생이름, 아이디, 학년, 제1전공 이름, 제2전공 이름이 나오게 하세요 (
-- 출력되게 하세요
-- ※ 스칼라 서브쿼리 활용.
select *
from t_student;
select *
from t_department;

SELECT name                                                                학생이름,
       id                                                                  아이디,
       grade                                                               학년,
       (SELECT dname FROM t_department WHERE deptno = deptno1)             제1전공,
       IFNULL((SELECT dname FROM t_department WHERE deptno = deptno2), '') 제2전공
FROM t_student s;























