
CREATE TABLE comment
(
  cid     INT          NOT NULL AUTO_INCREMENT COMMENT '댓글일련번호',
  content VARCHAR(100) NOT NULL COMMENT '내용',
  date    DATE         NOT NULL COMMENT '등록일',
  mid     INT          NOT NULL COMMENT '회원일련번호',
  pid     INT          NOT NULL COMMENT '게시글일련번호',
  PRIMARY KEY (cid)
) COMMENT '댓글';

CREATE TABLE friendship
(
  mid1 INT NOT NULL COMMENT '회원일련번호1',
  mid2 INT NOT NULL COMMENT '회원일련번호2',
  PRIMARY KEY (mid1, mid2)
) COMMENT '친구신청';

CREATE TABLE image
(
  name VARCHAR(10) NOT NULL COMMENT '사진명',
  path VARCHAR(50) NOT NULL COMMENT '사진 파일 경로',
  mid  INT         NOT NULL COMMENT '회원일련번호',
  pid  INT         NOT NULL COMMENT '게시글일련번호',
  PRIMARY KEY (mid, pid)
) COMMENT '사진';

CREATE TABLE like
(
  mid INT NOT NULL COMMENT '회원일련번호',
  pid INT NOT NULL COMMENT '게시글일련번호',
  PRIMARY KEY (mid, pid)
) COMMENT '좋아요';

CREATE TABLE member
(
  mid INT         NOT NULL AUTO_INCREMENT COMMENT '회원일련번호',
  id  VARCHAR(10) NOT NULL COMMENT '아이디',
  pw  VARCHAR(20) NOT NULL COMMENT '비밀번호',
  PRIMARY KEY (mid)
) COMMENT '회원';

ALTER TABLE member
  ADD CONSTRAINT UQ_id UNIQUE (id);

CREATE TABLE post
(
  pid   INT               NOT NULL AUTO_INCREMENT COMMENT '게시글일련번호',
  title VARCHAR(10)       NOT NULL COMMENT '제목',
  body  VARCHAR(100)      NOT NULL COMMENT '내용',
  level ENUM('공개', '비공개') NOT NULL COMMENT '공개수준',
  views INT               NULL     COMMENT '조회수',
  date  DATE              NOT NULL COMMENT '등록일',
  mid   INT               NOT NULL COMMENT '회원일련번호',
  PRIMARY KEY (pid)
) COMMENT '게시물';

ALTER TABLE post
  ADD CONSTRAINT FK_member_TO_post
    FOREIGN KEY (mid)
    REFERENCES member (mid);

ALTER TABLE friendship
  ADD CONSTRAINT FK_member_TO_friendship
    FOREIGN KEY (mid1)
    REFERENCES member (mid);

ALTER TABLE friendship
  ADD CONSTRAINT FK_member_TO_friendship1
    FOREIGN KEY (mid2)
    REFERENCES member (mid);

ALTER TABLE image
  ADD CONSTRAINT FK_post_TO_image
    FOREIGN KEY (pid)
    REFERENCES post (pid);

ALTER TABLE image
  ADD CONSTRAINT FK_member_TO_image
    FOREIGN KEY (mid)
    REFERENCES member (mid);

ALTER TABLE comment
  ADD CONSTRAINT FK_post_TO_comment
    FOREIGN KEY (pid)
    REFERENCES post (pid);

ALTER TABLE comment
  ADD CONSTRAINT FK_member_TO_comment
    FOREIGN KEY (mid)
    REFERENCES member (mid);

ALTER TABLE like
  ADD CONSTRAINT FK_member_TO_like
    FOREIGN KEY (mid)
    REFERENCES member (mid);

ALTER TABLE like
  ADD CONSTRAINT FK_post_TO_like
    FOREIGN KEY (pid)
    REFERENCES post (pid);
