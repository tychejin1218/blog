/*
// MySQL 컨테이너 BASH 접속
docker exec -it mysql /bin/bash

// MySQL에 root 계정으로 접속
mysql -u root -p

// Database 생성
CREATE DATABASE sample;

// 계정 생성
CREATE USER 'sample_user'@'%' IDENTIFIED BY 'password1!';

// 권한 확인
SHOW GRANTS FOR 'sample_user'@'%';

// 권한 설정
GRANT ALL PRIVILEGES ON sample.* TO 'sample_user'@'%';
*/

DROP TABLE IF EXISTS todo;

CREATE TABLE todo (
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255),
    description VARCHAR(255),
    completed BOOLEAN
);