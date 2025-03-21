-- ──────────────────────────────────────────────────────────
-- 1)
-- ↓ DDL 작성하기
-- ☞
create table TEST_STUDENT1
(
    stu_uid   int primary key auto_increment,
    stu_name  varchar(10) unique not null,
    stu_age   int,
    stu_grade TINYINT CHECK (stu_grade BETWEEN 1 AND 4),
    dept_uid  int
);

create table TEST_DEPARTMENT
(
    dept_uid   int primary key,
    dept_name  varchar(10) not null unique,
    dept_build varchar(4) check ( dept_build between 'K301' and 'A203' and 'B306')

);

-- ↓ DML 작성하기
-- ☞\
select *
from TEST_STUDENT1
select *
from TEST_DEPARTMENT

insert into TEST_STUDENT1 (stu_name, stu_age, stu_grade, dept_uid)
values ('고춘자', 20, 3, 1234),
       ('고길동', 30, 4, 3456);

insert into TEST_DEPARTMENT
values (1, '전자공학', 'K301'),
       (2, '영문학과' ,'A203');
select *
from TEST_DEPARTMENT
-- ──────────────────────────────────────────────────────────
-- 2)

-- ↓ 발생할수 있는 '이상증상'의 예를 '두가지 이상' 서술
-- ☞ 만약 예를 들어 회원 회원 고유번호 10인 사람이 회원 이름을 솔방울이라는 이름을 지우고 싶을떄
    -- 솔방울만 지워지는게 아니라 회원나이, 게시글 추천수 까지 다 지워지게 된다. 또한 회원고유번호 10번이
    -- 회원 나이를 올리고 싶을떄 게시글 번호 1001 에 있는 회원 나이만 수정되고 1003에 있는 회원 나이는 수정되지 않는다.
    -- 결국 삭제이상과 갱신 이상이 발생하게 된다

-- ↓ 테이블의 정규화 사유 기술
-- ☞ 정규화는 제 2 정규화 까지 가능하다 일단 1정규형을 가지고 있으며 부분적 함수 종속이 없기 떄문이다.

-- ↓ 정규화 결과 테이블 작성
-- [예시]

-- <고객테이블>
-- uid| name | age |
-- ───┼──────┼──────
--  1 | John | 23  |
--  2 | Sus  | 19  |

-- <구매테이블>
-- pid| price |uid |
-- ───┼───────┼──────
--  1 | 2000  | 2  |
--  2 | 1000  | 1  |

-- ☞
-- 개시글 번호| 게시글추천
-- ───┼───────┼
--  1001 | 44
--  1002 | 33
--  1003 | 22
--  1004 | 11
--  1005 | 55

-- 회원고유번호| 회원이름 | 회원나이
-- ───┼───────┼──────
--  10 | 솔방울| 25
--  20 | 김만두|22
--  40 | 박김치|22