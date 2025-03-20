-- #5102)연습
-- t_emp 테이블: 매니저별(MGR)로 관리하는 직원들의
-- ‘매니저’, ‘직원수’와 ‘급여총액’과 ‘급여평균’과 ‘교통비 (COMM) 평균’ 지급액 을 출력하세요.
-- 단 사장님은 (job = president)제외
select mgr 매니저, count(*) 직원수, sum(sal) 급여총액, avg(sal) 급여평균, round(avg(ifnull(comm, 0)))
from t_emp
where job != 'PRESIDENT'
group by mgr;

-- #5103)연습
-- t_professor 테이블 :  직위가 정교수 혹은 조교수 인 분들 중에서 ‘과별(deptno)’로
-- 과번호, 소속교수 총수, 근속일 평균, 급여평균, 보너스 평균을 출력해보세요
select DEPTNO, count(*) 총인원, round(avg(datediff(now(), HIREDATE))) 근속평균, round(avg(pay)) 급여평균, round(avg(bonus)) 보너스평균 from t_professor
where POSITION = '정교수' or POSITION = '조교수'
group by  DEPTNO;



-- #5104)연습
-- t_student 테이블 : 학과별(deptno1) 로,
-- 학과번호, 최대몸무게 - 최소몸무게 차이 값을 출력해보세요
select deptno1 학과번호, max(weight) - min(weight) 최대최소몸무게차
    from t_student
group by deptno1;


-- #5105) 그 차이가 30 이상인것만 출력하려면?
select deptno1 학과번호, max(weight) - min(weight) 최대최소몸무게차
from t_student
group by deptno1
having 최대최소몸무게차 >= 30;