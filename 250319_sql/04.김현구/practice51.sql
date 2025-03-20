-- #5102)연습
-- t_emp 테이블: 매니저별(MGR)로 관리하는 직원들의
-- ‘매니저’, ‘직원수’와 ‘급여총액’과 ‘급여평균’과 ‘교통비 (COMM) 평균’ 지급액 을 출력하세요.
-- 단 사장님은 (job = president)제외
select MGR 매니저, count(*) 직원수, sum(sal) 금여총액, format(avg(sal), 0) 급여평균, format(avg(ifnull(comm, 0)), 0) 교통비평균
from t_emp
where MGR != 'PRESIDENT'
group by MGR;


-- #5103)연습
-- t_professor 테이블 :  직위가 정교수 혹은 조교수 인 분들 중에서 ‘과별(deptno)’로
-- 과번호, 소속교수 총수, 근속일 평균, 급여평균, 보너스 평균을 출력해보세요
select DEPTNO,
       count(name)                        총인원,
       avg(datediff(curdate(), HIREDATE)) 근속평균,
       format(avg(pay), 0)                급여평균,
       format(avg(ifnull(bonus, 0)), 0)   보너스평균
from t_professor
where POSITION = '정교수'
   or POSITION = '조교수'
group by DEPTNO;


-- #5104)연습
-- t_student 테이블 : 학과별(deptno1) 로,
-- 학과번호, 최대몸무게 - 최소몸무게 차이 값을 출력해보세요
select * from t_student;

select deptno1 학과, max(weight) - min(weight) 최대최소몸무게차
from t_student
group by deptno1;

-- #5105) 그 차이가 30 이상인것만 출력하려면?
select deptno1 학과, max(weight) - min(weight) 최대최소몸무게차
from t_student
group by deptno1
having max(weight) - min(weight) >= 30;





















