
-- Users (회원)
CREATE TABLE Users (
                       num INT PRIMARY KEY AUTO_INCREMENT,
                       uid VARCHAR(50) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL
);

-- FriendRequests (친구 요청 및 친구 관계)
CREATE TABLE FriendRequests (
                                sender_id INT NOT NULL,
                                receiver_id INT NOT NULL,
                                status VARCHAR(100) CHECK(status in ('요청','수락')),
                                PRIMARY KEY (sender_id, receiver_id),
                                FOREIGN KEY (sender_id) REFERENCES Users(num),
                                FOREIGN KEY (receiver_id) REFERENCES Users(num)
);

-- Posts (게시물)
CREATE TABLE Posts (
                       post_id INT PRIMARY KEY AUTO_INCREMENT,  -- 고유한 일련번호
                       num INT NOT NULL,                    -- 작성자 (회원 ID, FK)
                       title VARCHAR(255) NOT NULL,             -- 제목
                       content TEXT NOT NULL,                   -- 내용
                       visibility VARCHAR(10) NOT NULL CHECK (visibility IN ('공개', '비공개')),
                       views INT ,
                       created_at VARCHAR(100),
                       FOREIGN KEY (num) REFERENCES Users(num)
);

-- Photos (게시물 사진)
CREATE TABLE Photos (
                        photo_id INT PRIMARY KEY AUTO_INCREMENT,
                        post_id INT NOT NULL,
                        filename VARCHAR(255) NOT NULL,
                        FOREIGN KEY (post_id) REFERENCES Posts(post_id)
);

-- Comments (댓글)
CREATE TABLE Comments (
                          comment_id INT PRIMARY KEY AUTO_INCREMENT, -- 고유한 일련번호
                          post_id INT NOT NULL,                      -- 게시물 ID (FK)
                          num INT NOT NULL,                      -- 작성자 (회원 ID, FK)
                          content TEXT NOT NULL,                     -- 댓글 내용
                          created_at VARCHAR(100) NOT NULL, -- 작성일
                          FOREIGN KEY (post_id) REFERENCES Posts(post_id),
                          FOREIGN KEY (num) REFERENCES Users(num)
);

-- Likes (좋아요)
CREATE TABLE Likes (
                       num INT NOT NULL,
                       post_id INT NOT NULL,
                       PRIMARY KEY (num, post_id), -- 좋아요는 토글이므로 복합키 설정
                       FOREIGN KEY (num) REFERENCES Users(num),
                       FOREIGN KEY (post_id) REFERENCES Posts(post_id)
);
