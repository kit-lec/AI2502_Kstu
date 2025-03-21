-- ──────────────────────────────────────────────────────────
-- 1)
-- ↓ DDL 작성하기
-- ☞
create table test_student(
    stu_uid int not null,
    stu_name varchar(10) not null,
    stu_age int,
    stu_grade int,
    dept_uid int,
    constraint t_stu_uid_pk primary key (stu_uid),
    constraint t_stu_age_ck check (stu_age >= 0),
    constraint t_stu_grade check ( stu_grade between 1 and 4),
    constraint t_stu_deptuid_fk foreign key (dept_uid) references test_department(dept_uid)
);

create table test_department(
    dept_uid int not null,
    dept_name varchar(20) not null,
    dept_build varchar(10),
    constraint t_dep_uid_pk primary key (dept_uid),
    constraint t_dep_name_uk unique (dept_name),
    constraint t_dep_build_ck check ( dept_build in ('K301', 'A203', 'B306') )
);

-- ↓ DML 작성하기
-- ☞
insert into test_department values(100, '컴퓨터공학과', 'K301'),
                                  (200, '성악과', 'A203'),
                                  (300, '경영학과', 'B306')
# select * from test_department;

insert into test_student values (1, 'aaa', 20, 3, 100),
                                (2, 'bbb', 25, 1, 200),
                                (3, 'ccc', 22, 2, 200),
                                (4, 'ddd', 24, 4, 300),
                                (5, 'fff', 22, 1, 100);

# select * from test_student;
-- ──────────────────────────────────────────────────────────
-- 2)

-- ↓ 발생할수 있는 '이상증상'의 예를 '두가지 이상' 서술
-- 1. 수정 이상: 김만두 회원의 나이를 23으로 바꾸고 싶어서 두번째 행을 수정하면..
-- 중복으로 인하여 마지막 행은 수정되지 않아 불일치성이 발생한다.
-- 2. 삭제 이상: 박김치 회원의 게시글을 삭제하고 싶어서 4번째 행을 삭제하면...
-- 게시글에 관한 정보뿐 아니라 박김치 회원에 관한 모든 정보가 삭제된다.
-- 3. 삽입 이상: 새로운 회원이 생겨 회원에 관한 정보만 (예: 회원고유번호: 50, 회원이름: 곱창전골) 추가하고 싶은데..
-- 게시글번호가 기본키라 회원에 관한 정보를 추가할 수가 없다.

-- ↓ 테이블의 정규화 사유 기술
-- 회원고유번호 --> 회원이름, 회원나이가 함수적 종속관계인데..
-- 이 때 기본키는 (게시글번호, 회원고유번호) 로 구성된 복합키이다.
-- 그런데 회원고유번호에 대해 두 칼럼이 부분적으로 종속됨.
-- 따라서 제 2 정규화를 수행해야 하는 상황.

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
-- <회원테이블>
-- 회원고유번호(PK) | 회원이름 | 회원나이
-- ──────────────┼─────────┼────────
--    10         / 솔방울   /  25
--    20         / 김만두   /  22
--    40         / 박김치   /  22

-- <게시글테이블>
-- 게시글번호(PK) | 게시글추천수 | 회원고유번호(FK)
-- ─────────────┼───────────┼────────────────
--    1001      /    44     /     10
--    1002      /    33     /     20
--    1003      /    22     /     10
--    1004      /    11     /     40
--    1005      /    55     /     20