-- #7109) 연습
-- t_student 테이블 :
-- 전체학생중에서 체중이 4학년 학생들의 체중에서
-- 가장 적게 나가는 학생보다
-- 몸무게가 적은 학생의 이름과 학년과 몸무게를 출력하세요

select weight
from t_student
where grade = 4
order by weight
;

select name   이름,
       grade  학년,
       weight 몸무게
from t_student
where weight < all (select weight from t_student where grade = 4)
order by weight
;


-- #7110) 연습
-- t_emp2, t_dept2 테이블 :
-- 각 부서별 평균 연봉을 구하고 그 중에서 평균 연봉이 가장 적은 부서의 평균연봉보다
-- 적게 받는 직원들의 부서명, 직원명, 연봉을 출력 하세요


select avg(pay)
from t_emp2
group by deptno
order by deptno;

select d.dname 부서명,
       e.name  직원명,
       e.pay   연봉
from t_emp2 e,
     t_dept2 d
where e.pay < all
      (select avg(pay)
       from t_emp2
       group by deptno
       order by deptno)
  and e.deptno = d.dcode
order by e.pay;


-- #7202) 연습
-- t_professor , t_department 테이블 :
-- 각 학과별로 입사일이 가장 오래된 교수의
-- 교수번호와 이름, 입사일, 학과명을 출력하세요.
-- 단 학과이름 순으로 오름차순 정렬하세요

select min(hiredate)
from t_professor
group by deptno
;

select p.profno   교수번호,
       p.name     이름,
       p.hiredate 입사일,
       d.dname    학과명
from t_professor p,
     t_department d
where hiredate in
      (select min(hiredate)
       from t_professor
       group by deptno)
  and p.deptno = d.deptno
order by d.dname
;

-- #7203) 연습
-- t_emp2 테이블 :
-- 직급별로 해당직급에서 최대 연봉을 받는 직원의
-- 이름과 직급, 연봉을 출력하세요,
-- 단, 연봉순으로 오름차순 정렬하세요

select post, max(pay)
from t_emp2
group by post;

select e.name             이름,
       ifnull(e.post, '') 직급,
       e.pay * 12         연봉
from t_emp2 e
where pay in
      (select max(pay)
       from t_emp2
       group by post)
order by pay
;

-- #7207) 예제
-- t_student, t_department 테이블 사용
-- 학생이름, 아이디, 학년, 제1전공 이름, 제2전공 이름이 나오게 하세요 (
-- 출력되게 하세요
-- ※ 스칼라 서브쿼리 활용.

select *
from t_student;
select *
from t_department;

select s.name   이름,
       s.id     아이디,
       s.grade  학년,
       ifnull(d1.dname,'') 제1전공,
       ifnull(d2.dname,'') 제2전공
from t_student s
         left join t_department d1 on s.deptno1 = d1.deptno
         left join t_department d2 on s.deptno2 = d2.deptno
;




