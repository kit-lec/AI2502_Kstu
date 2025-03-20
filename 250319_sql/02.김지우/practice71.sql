-- #7109) 연습
-- t_student 테이블 :
-- 전체학생중에서 체중이 4학년 학생들의 체중에서
-- 가장 적게 나가는 학생보다
-- 몸무게가 적은 학생의 이름과 학년과 몸무게를 출력하세요
select *
from t_student;

-- subquery
# select min(weight) from t_student where grade = 4;

select name 이름, grade 학년, weight 몸무게
from t_student
where weight < (select min(weight) from t_student where grade = 4);



-- #7110) 연습
-- t_emp2, t_dept2 테이블 :
-- 각 부서별 평균 연봉을 구하고 그 중에서 평균 연봉이 가장 적은 부서의 평균연봉보다
-- 적게 받는 직원들의 부서명, 직원명, 연봉을 출력 하세요

-- 일단 부서별 평균 연봉
# select round(avg(pay))
# from t_emp2
# group by DEPTNO;

-- MySQL
select d.DNAME 부서명, e.NAME 직원명, e.PAY 연봉
from t_emp2 e,
     t_dept2 d
where e.DEPTNO = d.DCODE
  and e.pay < all (select round(avg(pay))
                   from t_emp2
                   group by DEPTNO);

-- ANSI
select d.DNAME 부서명, e.NAME 직원명, e.PAY 연봉
from t_emp2 e
         join t_dept2 d
              on e.DEPTNO = d.DCODE
where e.pay < all (select round(avg(pay))
                   from t_emp2
                   group by DEPTNO);



-- #7202) 연습
-- t_professor , t_department 테이블 :
-- 각 학과별로 입사일이 가장 오래된 교수의
-- 교수번호와 이름, 입사일, 학과명을 출력하세요.
-- 단 학과이름 순으로 오름차순 정렬하세요

-- MySQL
select p.PROFNO 교수번호, p.NAME 교수명, p.HIREDATE 입사일, d.dname 학과명
    from t_professor p, t_department d
where p.DEPTNO = d.deptno and (p.DEPTNO, p.HIREDATE) in (select DEPTNO,  min(HIREDATE)
                                                         from t_professor
                                                         group by DEPTNO)
order by d.dname;

-- ANSI
select p.PROFNO 교수번호,
       p.NAME 교수명,
       p.HIREDATE 입사일,
       d.dname 학과명
from t_professor p join t_department d
on p.DEPTNO = d.deptno
where (p.DEPTNO, p.HIREDATE) in (select DEPTNO, min(HIREDATE)
                                 from t_professor
                                 group by DEPTNO)
order by d.dname;

-- 학과 별로 입사일이 가장 오래된 교수?(subquery)
# select DEPTNO,  min(HIREDATE)
# from t_professor
# group by DEPTNO;



-- #7203) 연습
-- t_emp2 테이블 :
-- 직급별로 해당직급에서 최대 연봉을 받는 직원의
-- 이름과 직급, 연봉을 출력하세요,
-- 단, 연봉순으로 오름차순 정렬하세요
select NAME 이름, POST 직급, PAY 연봉
    from t_emp2
where (post, pay) in (select POST,max(pay)
                      from t_emp2
                      group by POST)
order by pay;

-- 직급별 최대연봉
# select POST,max(pay)
# from t_emp2
# group by POST;



-- #7207) 예제
-- t_student, t_department 테이블 사용
-- 학생이름, 아이디, 학년, 제1전공 이름, 제2전공 이름이 나오게 하세요 (
-- 출력되게 하세요
-- ※ 스칼라 서브쿼리 활용.

-- 제 1전공 null이 아니니 얘로 조인 ㄱㄱ
select s.name 학생이름,
       s.id 아이디,
       s.grade 학년,
       d.dname 제1전공,
       ifnull((select d_sub.dname from t_student s_sub, t_department d_sub where s_sub.deptno2 = d_sub.deptno and s_sub.studno = s.studno), '')
    from t_student s, t_department d
where s.deptno1 = d.deptno;

-- 헷갈리니 제 2전공도 학생마다 맞게 넣기 위해 쿼리를 만들어보자요
# select s_sub.name , ifnull(d_sub.dname, '')
# from t_student s_sub left outer join t_department d_sub
# on s_sub.deptno2 = d_sub.deptno;