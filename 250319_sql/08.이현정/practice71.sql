-- https://docs.google.com/presentation/d/1Fhaj2Q0Mua_ARhNei6yCj110L3GV43Zo9gbIciiLYks/edit#slide=id.ge8b17e2d84_0_269


# 7109) 연습
# t_student 테이블 :
# 전체학생중에서 체중이 4학년 학생들의 체중에서
# 가장 적게 나가는 학생보다
# 몸무게가 적은 학생의 이름과 학년과 몸무게를 출력하세요
SELECT name 이름, grade 학년, weight 몸무게 FROM t_student
WHERE weight < (SELECT min(weight) FROM t_student WHERE grade = 4);


# 7110) 연습
# t_emp2, t_dept2 테이블 :
# 각 부서별 평균 연봉을 구하고 그 중에서 평균 연봉이 가장 적은 부서의 평균연봉보다
# 적게 받는 직원들의 부서명, 직원명, 연봉을 출력 하세요
SELECT d.DNAME 부서명, e.NAME 직원명, e.PAY 연봉
FROM t_emp2 e, t_dept2 d
WHERE e.DEPTNO = d.DCODE
    AND e.pay <All (SELECT avg(PAY) FROM t_emp2
                  GROUP BY deptno);


# 7202) 연습
# t_professor , t_department 테이블 :
# 각 학과별로 입사일이 가장 오래된 교수의 교수번호와 이름, 입사일, 학과명을 출력하세요.
# 단, 학과이름 순으로 오름차순 정렬하세요
SELECT p.PROFNO 교수번호, p.NAME 교수명, p.HIREDATE 입사일, d.dname 학과명
FROM t_professor p, t_department d
WHERE p.DEPTNO = d.deptno
    AND (d.deptno, p.HIREDATE) IN (SELECT DEPTNO, min(HIREDATE)
                      FROM t_professor
                      GROUP BY DEPTNO)
ORDER BY d.dname;


# 7203) 연습
# t_emp2 테이블 :
# 직급별로 해당직급에서 최대 연봉을 받는 직원의 이름과 직급, 연봉을 출력하세요.
# 단, 연봉순으로 오름차순 정렬하세요
SELECT NAME 이름, POST 직급, PAY 연봉
FROM t_emp2 a
WHERE a.PAY = (SELECT max(pay) FROM t_emp2 b WHERE a.POST = b.POST)
ORDER BY PAY;


# 7207) 예제
# t_student, t_department 테이블 사용
# 학생이름, 아이디, 학년, 제1전공 이름, 제2전공 이름이 나오게 하세요
# ※ 스칼라 서브쿼리 활용.
SELECT name, id, grade,
       (SELECT dname FROM t_department WHERE deptno = s.deptno1) dname,
       ifnull((SELECT dname FROM t_department WHERE deptno = s.deptno2), '') dname2
FROM t_student s;
