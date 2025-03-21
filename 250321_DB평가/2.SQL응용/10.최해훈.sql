-- ──────────────────────────────────────────────────────────
-- 1)
-- ↓ DDL 작성하기
-- ☞
CREATE TABLE TEST_STUDENT
(
    stu_id    int PRIMARY KEY AUTO_INCREMENT,
    stu_name  varchar(80) NOT NULL,
    stu_age   int(200),
    stu_grade int(10),
    dept_uid int(100) REFERENCES t_dept2 (dcode),
    CONSTRAINT stu_grade_ck CHECK ( stu_grade <= 4 AND stu_grade > 0)
);

CREATE TABLE TEST_DEPARTMENT
(
    dept_uid  int PRIMARY KEY,
    dept_name VARCHAR(80),
    dept_build VARCHAR(5),
    CONSTRAINT dept_build_ck CHECK (dept_build IN ('K301' or 'A203' or 'B306'))
);

-- ↓ DML 작성하기
-- ☞
INSERT INTO TEST_DEPARTMENT (dept_uid, dept_name, dept_build)
VALUES ('1','컴퓨터공학과', 'K301'),
       ('2','기계공학과', 'A203'),
       ('3','전자공학과', 'B306')
;

INSERT INTO TEST_STUDENT (stu_id, stu_name, stu_age, stu_grade, dept_uid)
VALUES ('10', '홍길동', '24', '2', '2'),
       ('11', '최해훈', '20', '1', '2'),
       ('12', '해최훈', '22', '3', '1'),
       ('13', '훈해최', '22', '1', '3'),
       ('14', '동길홍', '23', '2', '1')
;

-- ──────────────────────────────────────────────────────────
-- 2)

-- ↓ 발생할수 있는 '이상증상'의 예를 '두가지 이상' 서술
-- ☞ 게시글번호 1005 를 삭제하면 김만두의 정보가 사라짐
-- ☞ 게시글번호 1001인 솔방울의 나이가 1살 추가되면 1003 솔방울의 나이가 갱신안됌

-- ↓ 테이블의 정규화 사유 기술
-- ☞ 제 3정규형 = 이행적 함수 종속 제거


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
-- 회원고유번호 |  회원이름  | 회원나이
-- ────────────┼───────────┼──────
--     10      |   솔방울  |   25
--     20      |   김만두  |   22
--     40      |   박김치  |   22

-- 게시글번호 |  회원고유번호  | 게시글추천수
-- ──────────┼────────────────┼───────────
--    1001   |       10       |     44
--    1002   |       20       |     33
--    1003   |       10       |     22
--    1004   |       40       |     11
--    1005   |       20       |     55