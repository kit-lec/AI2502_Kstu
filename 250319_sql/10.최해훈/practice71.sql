-- #7109) 연습
-- t_student 테이블 :
-- 전체학생중에서 체중이 4학년 학생들의 체중에서
-- 가장 적게 나가는 학생보다
-- 몸무게가 적은 학생의 이름과 학년과 몸무게를 출력하세요
SELECT weight
FROM t_student
WHERE grade = 4;
SELECT MIN(weight)
FROM t_student
WHERE grade = 4;

SELECT name 이름, grade 학년, weight 몸무게
FROM t_student
WHERE weight < (SELECT MIN(weight) FROM t_student WHERE grade = 4)
;

-- #7110) 연습
-- t_emp2, t_dept2 테이블 :
-- 각 부서별 평균 연봉을 구하고 그 중에서 평균 연봉이 가장 적은 부서의 평균연봉보다
-- 적게 받는 직원들의 부서명, 직원명, 연봉을 출력 하세요
###### t_emp2 deptno pay
###### t_dept2 dcode dname
SELECT *
FROM t_emp2;
SELECT *
FROM t_dept2;

SELECT round(avg(pay), 0) "AVG(PAY)" FROM t_emp2 group by deptno;

SELECT d.dname 부서명, e.name 직원명, e.pay 연봉 FROM t_emp2 e, t_dept2 d
WHERE d.dcode = e.deptno AND
      e.pay < ALL (SELECT round(avg(pay), 0) "AVG(PAY)" FROM t_emp2 group by deptno)
;

-- #7202) 연습
-- t_professor , t_department 테이블 :
-- 각 학과별로 입사일이 가장 오래된 교수의
-- 교수번호와 이름, 입사일, 학과명을 출력하세요.
-- 단 학과이름 순으로 오름차순 정렬하세요
SELECT * FROM t_professor;
SELECT * FROM t_department;

SELECT deptno, MIN(hiredate) FROM t_professor group by deptno;

SELECT p.profno 교수번호, p.name 교수명, p.hiredate 입사일, d.dname 학과명
FROM t_professor p, t_department d
WHERE p.deptno = d.deptno AND
      p.hiredate = (SELECT MIN(hiredate) FROM t_professor p2 where p2.deptno = p.deptno)
;

-- #7203) 연습
-- t_emp2 테이블 :
-- 직급별로 해당직급에서 최대 연봉을 받는 직원의
-- 이름과 직급, 연봉을 출력하세요,
-- 단, 연봉순으로 오름차순 정렬하세요
SELECT * FROM t_emp2;
SELECT post, MAX(pay) FROM t_emp2 GROUP BY post;

SELECT name 사원명, post 직급, pay 연봉
FROM t_emp2 e
WHERE e.pay = (SELECT MAX(e1.pay) FROM t_emp2 e1 WHERE e.post = e1.post)
ORDER BY e.pay ASC
;


-- #7207) 예제
-- t_student, t_department 테이블 사용
-- 학생이름, 아이디, 학년, 제1전공 이름, 제2전공 이름이 나오게 하세요
-- ※ 스칼라 서브쿼리 활용.
SELECT *
FROM t_department;
SELECT *
FROM t_student;

SELECT s.name, ifnull(d.dname, ' ') FROM t_student s, t_department d
WHERE s.deptno2 = d.deptno
;


SELECT s.name, s.id, s.grade, d1.dname, ifnull(d2.dname, ' ') "dname2"
FROM t_student s, t_department d1, t_department d2
WHERE s.deptno1 = d1.deptno AND
      (s.deptno2 = d2.deptno OR s.deptno2 is null)
;