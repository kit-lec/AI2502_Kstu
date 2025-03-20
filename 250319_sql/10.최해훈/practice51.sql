-- #5102)연습
-- t_emp 테이블: 매니저별(MGR)로 관리하는 직원들의
-- ‘매니저’, ‘직원수’와 ‘급여총액’과 ‘급여평균’과 ‘교통비 (COMM) 평균’ 지급액 을 출력하세요.
-- 단 사장님은 (job = president)제외
SELECT MGR                             매니저,
       count(MGR)                      직원수,
       sum(SAL)                        급여총액,
       round(sum(SAL) / count(MGR), 0) 급여평균,
       round(avg(ifnull(COMM, 0)), 0)  교통비평균
FROM t_emp
WHERE not job = 'president'
GROUP BY MGR
;



-- #5103)연습
-- t_professor 테이블 :  직위가 정교수 혹은 조교수 인 분들 중에서 ‘과별(deptno)’로
-- 과번호, 소속교수 총수, 근속일 평균, 급여평균, 보너스 평균을 출력해보세요
SELECT DEPTNO,
       count(DEPTNO)                            총인원,
       avg(timestampdiff(day, HIREDATE, now())) 근속평균,
       round(avg(pay), 0)                       급여평균,
       round(avg(ifnull(bonus, 0)), 0)          보너스평균
FROM t_professor
WHERE POSITION = '정교수'
   OR POSITION = '조교수'
GROUP BY DEPTNO
;


-- #5104)연습
-- t_student 테이블 : 학과별(deptno1) 로,
-- 학과번호, 최대몸무게 - 최소몸무게 차이 값을 출력해보세요
SELECT deptno1                   학과,
       MAX(weight) - MIN(weight) 최대최소몸무게차
FROM t_student
GROUP BY deptno1
;

-- #5105) 그 차이가 30 이상인것만 출력하려면?
SELECT deptno1                   학과,
       MAX(weight) - MIN(weight) 최대최소몸무게차
FROM t_student
GROUP BY deptno1
HAVING (MAX(weight) - MIN(weight)) >= 30
;