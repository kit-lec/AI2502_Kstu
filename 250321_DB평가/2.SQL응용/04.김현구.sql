-- ──────────────────────────────────────────────────────────
-- 1)
-- ↓ DDL 작성하기
-- ☞
create table TEST_DEPARTMENT
(
    dept_uid   INT primary key unique not null,
    dept_name  VARCHAR(10) unique     not null,
    dept_build VARCHAR(10)            not null
);
CREATE TABLE TEST_STUDENT
(
    stu_uid   int(10) PRIMARY KEY unique not null,
    stu_name  VARCHAR(50)                NOT NULL,
    stu_age   INT CHECK (stu_age >= 0),
    stu_grade INT CHECK (stu_grade BETWEEN 1 AND 4),
    dept_uid  VARCHAR(10),
    FOREIGN KEY (dept_uid) REFERENCES TEST_DEPARTMENT (dept_uid)
);

-- ↓ DML 작성하기
-- ☞
insert into TEST_DEPARTMENT (dept_uid, dept_name, dept_build)
values ('A001', '신소재공학과', 'K301'),
       ('A002', '컴퓨터공학과', 'A203'),
       ('A003', '영문과', 'B306');

insert into TEST_STUDENT (stu_uid, stu_name, stu_age, stu_grade, dept_uid)
values ('S001', '김철수', '24', '3', 'A001'),
       ('S002', '김영희', '22', '1', 'A003'),
       ('S003', '이재인', '27', '4', 'A001'),
       ('S004', '김재명', '20', '2', 'A002'),
       ('S005', '오태식', '23', '4', 'A003')


-- ──────────────────────────────────────────────────────────
-- 2)

-- ↓ 발생할수 있는 '이상증상'의 예를 '두가지 이상' 서술
-- ☞
# 삽입 이상 (Insertion Anomaly)
# 새로운 회원이 가입했지만, 아직 게시글을 작성하지 않았다면 회원 정보를 저장할 수 없음
#
# 삭제 이상 (Deletion Anomaly)
# 박김치가 작성한 게시글(1004)이 삭제되면, 회원 정보도 함께 사라짐


-- ↓ 테이블의 정규화 사유 기술
-- ☞
#     ‘게시글 추천수’ 는 기본키(게시글번호, 회원고유번호) 에 완전 함수적 종속이지만,
#     ‘회원이름’ 와 ‘회원나이’ 는  (회원고유번호) 에 의해서도 결정될수 있으므로 기본키에 대해 완전 종속은 아니다.
#     따라서 완전 함수적 종속이 아니다 ->> 제 2정규형을 만들어야한다.

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
-- 회원고유번호 | 회원이름 | 회원나이
-- ────────────┼─────────┼─────────
--     10      |  솔방울  |   25
--     20      |  김만두  |   22
--     40      |  박김치  |   22

-- <게시글테이블>
-- 게시글번호   | 회원고유번호 | 게시글추천수
-- ────────────┼─────────────┼─────────────
--   1001      |     10      |   44
--   1002      |     20      |   33
--   1003      |     10      |   22
--   1004      |     40      |   11
--   1005      |     20      |   55