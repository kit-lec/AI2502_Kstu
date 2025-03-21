
CREATE TABLE comment
(
  comid   INT          NOT NULL AUTO_INCREMENT,
  content VARCHAR(100) NOT NULL,
  cre_at  VARCHAR(10)  NOT NULL,
  postno  int          NOT NULL,
  memno   INT          NOT NULL,
  PRIMARY KEY (comid)
) COMMENT '댓글';

ALTER TABLE comment
  ADD CONSTRAINT UQ_comid UNIQUE (comid);

CREATE TABLE friend
(
  memno1 INT NOT NULL,
  memno2 INT NOT NULL,
  PRIMARY KEY (memno1, memno2)
) COMMENT '친구맺기';

CREATE TABLE like
(
  memno  INT NOT NULL,
  postno int NOT NULL,
  PRIMARY KEY (memno, postno)
) COMMENT '좋아요';

ALTER TABLE like
  ADD CONSTRAINT UQ_memno UNIQUE (memno);

CREATE TABLE member
(
  memno    INT         NOT NULL AUTO_INCREMENT,
  id       VARCHAR(20) NOT NULL,
  password VARCHAR(20) NOT NULL,
  PRIMARY KEY (memno)
) COMMENT '회원';

ALTER TABLE member
  ADD CONSTRAINT UQ_memno UNIQUE (memno);

ALTER TABLE member
  ADD CONSTRAINT UQ_id UNIQUE (id);

CREATE TABLE photo
(
  photoid INT NOT NULL AUTO_INCREMENT,
              NULL    ,
              NULL    ,
  postno  int NOT NULL,
  PRIMARY KEY (photoid)
) COMMENT '사진';

ALTER TABLE photo
  ADD CONSTRAINT UQ_photoid UNIQUE (photoid);

CREATE TABLE post
(
  postno     int          NOT NULL AUTO_INCREMENT,
  title      VARCHAR(20)  NOT NULL,
  content    VARCHAR(200) NOT NULL,
  visibility BOOLEAN      NOT NULL,
  views      VARCHAR(10)  NOT NULL,
  cre_at     VARCHAR(10)  NOT NULL,
  memno      INT          NOT NULL,
  PRIMARY KEY (postno)
) COMMENT '게시물';

ALTER TABLE post
  ADD CONSTRAINT UQ_postno UNIQUE (postno);

ALTER TABLE post
  ADD CONSTRAINT FK_member_TO_post
    FOREIGN KEY (memno)
    REFERENCES member (memno);

ALTER TABLE friend
  ADD CONSTRAINT FK_member_TO_friend
    FOREIGN KEY (memno1)
    REFERENCES member (memno);

ALTER TABLE friend
  ADD CONSTRAINT FK_member_TO_friend1
    FOREIGN KEY (memno2)
    REFERENCES member (memno);

ALTER TABLE comment
  ADD CONSTRAINT FK_post_TO_comment
    FOREIGN KEY (postno)
    REFERENCES post (postno);

ALTER TABLE comment
  ADD CONSTRAINT FK_member_TO_comment
    FOREIGN KEY (memno)
    REFERENCES member (memno);

ALTER TABLE photo
  ADD CONSTRAINT FK_post_TO_photo
    FOREIGN KEY (postno)
    REFERENCES post (postno);

ALTER TABLE like
  ADD CONSTRAINT FK_member_TO_like
    FOREIGN KEY (memno)
    REFERENCES member (memno);

ALTER TABLE like
  ADD CONSTRAINT FK_post_TO_like
    FOREIGN KEY (postno)
    REFERENCES post (postno);
