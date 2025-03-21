
CREATE TABLE comment
(
  com_cont VARCHAR(100) NULL     COMMENT '내용',
  com_date DATE         NOT NULL DEFAULT now() COMMENT '작성일',
  com_num  INT          NOT NULL AUTO_INCREMENT COMMENT '일련번호',
           INT          NOT NULL,
  PRIMARY KEY (com_num, )
) COMMENT '댓글';

CREATE TABLE create_com
(
  com_num INT NOT NULL AUTO_INCREMENT COMMENT '일련번호',
  PRIMARY KEY (com_num)
) COMMENT '댓글작성';

CREATE TABLE create_post
(
  post_num  NOT NULL AUTO_INCREMENT COMMENT '일련번호',
  PRIMARY KEY (post_num)
) COMMENT '게시물작성';

CREATE TABLE picture
(
  info     VARCHAR(30) NOT NULL COMMENT '파일정보',
  post_num INT         NOT NULL COMMENT '일련번호'
) COMMENT '사진';

CREATE TABLE post
(
  title    VARCHAR(20)   NOT NULL COMMENT '제목',
  content  VARCHAR(1000) NULL     COMMENT '내용',
  postdate DATE          NOT NULL DEFAULT now() COMMENT '등록일',
  post_num INT           NOT NULL AUTO_INCREMENT COMMENT '일련번호',
                         NOT NULL,
  PRIMARY KEY (post_num, )
) COMMENT '게시물';

CREATE TABLE user
(
  id       VARCHAR(100) NOT NULL COMMENT '아이디',
  password VARCHAR(100) NOT NULL COMMENT '패스워드',
  user_num INT          NOT NULL AUTO_INCREMENT COMMENT '일련번호',
  user     INT          NOT NULL,
                        NOT NULL,
  PRIMARY KEY (user_num, user, )
) COMMENT '회원';

ALTER TABLE user
  ADD CONSTRAINT UQ_id UNIQUE (id);

ALTER TABLE picture
  ADD CONSTRAINT FK_post_TO_picture
    FOREIGN KEY (post_num)
    REFERENCES post (post_num);

ALTER TABLE comment
  ADD CONSTRAINT FK_create_com_TO_comment
    FOREIGN KEY ()
    REFERENCES create_com (com_num);

ALTER TABLE user
  ADD CONSTRAINT FK_create_post_TO_user
    FOREIGN KEY ()
    REFERENCES create_post (post_num);

ALTER TABLE post
  ADD CONSTRAINT FK_create_post_TO_post
    FOREIGN KEY ()
    REFERENCES create_post (post_num);

ALTER TABLE user
  ADD CONSTRAINT FK_create_com_TO_user
    FOREIGN KEY (user)
    REFERENCES create_com (com_num);
