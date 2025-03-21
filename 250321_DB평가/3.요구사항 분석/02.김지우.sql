
CREATE TABLE comment
(
  comno   INT          NOT NULL,
  content VARCHAR(100) NOT NULL,
  date    DATE         NOT NULL,
  postno  INT          NOT NULL,
  userno  INT          NOT NULL,
  PRIMARY KEY (comno)
) COMMENT '댓글';

CREATE TABLE file
(
   fileno INT          NOT NULL AUTO_INCREMENT,
  name    varchar(100) NOT NULL,
  PRIMARY KEY ( fileno)
) COMMENT '사진첨부';

CREATE TABLE friend
(
  friendno       INT NOT NULL,
  userno         INT NOT NULL,
  friendrelation INT NOT NULL,
  PRIMARY KEY (friendrelation)
) COMMENT '친구';

CREATE TABLE post
(
  postno  INT          NOT NULL AUTO_INCREMENT,
  title   VARCHAR(20)  NOT NULL,
  content VARCHAR(500) NULL    ,
  public  BOOLEAN      NOT NULL COMMENT 'true면 공개 false면 비공개',
  view    INT          NULL    ,
  date    DATE         NOT NULL,
  userno  INT          NOT NULL,
  PRIMARY KEY (postno)
) COMMENT '게시물';

CREATE TABLE post_file
(
  postno  INT NOT NULL,
   fileno INT NOT NULL,
  PRIMARY KEY (postno,  fileno)
) COMMENT '게시물의 사진들';

CREATE TABLE post_like
(
  userno INT NOT NULL,
  postno INT NOT NULL,
  PRIMARY KEY (userno, postno)
) COMMENT '좋아요기능';

CREATE TABLE user
(
  userno         INT         NOT NULL AUTO_INCREMENT,
  id             VARCHAR(50) NOT NULL,
  password       VARCHAR(20) NOT NULL,
  friendrelation INT         NULL    ,
  PRIMARY KEY (userno)
) COMMENT '회원';

ALTER TABLE user
  ADD CONSTRAINT UQ_id UNIQUE (id);

ALTER TABLE post
  ADD CONSTRAINT FK_user_TO_post
    FOREIGN KEY (userno)
    REFERENCES user (userno);

ALTER TABLE post_file
  ADD CONSTRAINT FK_post_TO_post_file
    FOREIGN KEY (postno)
    REFERENCES post (postno);

ALTER TABLE post_file
  ADD CONSTRAINT FK_file_TO_post_file
    FOREIGN KEY ( fileno)
    REFERENCES file ( fileno);

ALTER TABLE comment
  ADD CONSTRAINT FK_post_TO_comment
    FOREIGN KEY (postno)
    REFERENCES post (postno);

ALTER TABLE comment
  ADD CONSTRAINT FK_user_TO_comment
    FOREIGN KEY (userno)
    REFERENCES user (userno);

ALTER TABLE post_like
  ADD CONSTRAINT FK_user_TO_post_like
    FOREIGN KEY (userno)
    REFERENCES user (userno);

ALTER TABLE post_like
  ADD CONSTRAINT FK_post_TO_post_like
    FOREIGN KEY (postno)
    REFERENCES post (postno);

ALTER TABLE user
  ADD CONSTRAINT FK_friend_TO_user
    FOREIGN KEY (friendrelation)
    REFERENCES friend (friendrelation);
