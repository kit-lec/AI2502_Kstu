
CREATE TABLE friends
(
  fr_follower int     NOT NULL COMMENT '회원번호',
  fr_asked    int     NOT NULL COMMENT '회원번호',
  fr_accept   boolean NOT NULL DEFAULT false COMMENT '수락여부',
  PRIMARY KEY (fr_follower, fr_asked)
) COMMENT '친구';

CREATE TABLE liked
(
  post_no int NOT NULL COMMENT '게시물번호',
  user_no int NOT NULL COMMENT '회원번호',
  PRIMARY KEY (post_no, user_no)
) COMMENT '좋아요';

CREATE TABLE photo
(
  photo_no   int          NOT NULL COMMENT '사진번호',
  post_no    int          NOT NULL COMMENT '게시물번호',
  user_no    int          NOT NULL COMMENT '회원번호',
  photo_file varchar(255) NOT NULL COMMENT '사진파일명',
  photo_date datetime     NOT NULL DEFAULT now() COMMENT '게시일자',
  PRIMARY KEY (photo_no)
) COMMENT '사진';

CREATE TABLE post
(
  post_no    int          NOT NULL COMMENT '게시물번호',
  user_no    int          NOT NULL COMMENT '회원번호',
  post_title varchar(255) NOT NULL COMMENT '제목',
  post_main  mediumtext   NULL     COMMENT '내용',
  post_open  boolean      NULL     DEFAULT true COMMENT '공개수준',
  post_view  int          NOT NULL DEFAULT 0 COMMENT '조회수',
  post_date  datetime     NOT NULL DEFAULT now() COMMENT '등록일',
  PRIMARY KEY (post_no)
) COMMENT '게시물';

CREATE TABLE reply
(
  reply_no   int      NOT NULL COMMENT '댓글번호',
  post_no    int      NOT NULL COMMENT '게시물번호',
  user_no    int      NOT NULL COMMENT '회원번호',
  reply_main text     NULL     COMMENT '내용',
  reply_date datetime NULL     DEFAULT now() COMMENT '작성일',
  PRIMARY KEY (reply_no)
) COMMENT '댓글';

CREATE TABLE user
(
  user_no   int          NOT NULL AUTO_INCREMENT COMMENT '회원번호',
  user_id   varchar(100) NOT NULL COMMENT '아이디',
  user_pw   varchar(127) NOT NULL COMMENT '패스워드',
  user_date datetime     NOT NULL DEFAULT now() COMMENT '가입일',
  PRIMARY KEY (user_no)
) COMMENT '회원';

ALTER TABLE user
  ADD CONSTRAINT UQ_user_id UNIQUE (user_id);

ALTER TABLE post
  ADD CONSTRAINT FK_user_TO_post
    FOREIGN KEY (user_no)
    REFERENCES user (user_no);

ALTER TABLE reply
  ADD CONSTRAINT FK_post_TO_reply
    FOREIGN KEY (post_no)
    REFERENCES post (post_no);

ALTER TABLE photo
  ADD CONSTRAINT FK_post_TO_photo
    FOREIGN KEY (post_no)
    REFERENCES post (post_no);

ALTER TABLE reply
  ADD CONSTRAINT FK_user_TO_reply
    FOREIGN KEY (user_no)
    REFERENCES user (user_no);

ALTER TABLE friends
  ADD CONSTRAINT FK_user_TO_friends
    FOREIGN KEY (fr_follower)
    REFERENCES user (user_no);

ALTER TABLE friends
  ADD CONSTRAINT FK_user_TO_friends1
    FOREIGN KEY (fr_asked)
    REFERENCES user (user_no);

ALTER TABLE photo
  ADD CONSTRAINT FK_user_TO_photo
    FOREIGN KEY (user_no)
    REFERENCES user (user_no);

ALTER TABLE liked
  ADD CONSTRAINT FK_user_TO_liked
    FOREIGN KEY (user_no)
    REFERENCES user (user_no);

ALTER TABLE liked
  ADD CONSTRAINT FK_post_TO_liked
    FOREIGN KEY (post_no)
    REFERENCES post (post_no);
