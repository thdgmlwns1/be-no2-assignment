



CREATE TABLE schedule (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          author VARCHAR(255),
                          password VARCHAR(255),
                          task VARCHAR(255),
                          created_at DATETIME,
                          updated_at DATETIME
);

CREATE TABLE author (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(100) ,
                        email VARCHAR(255),
                        createdAt DATETIME,
                        updatedAt DATETIME
);

CREATE TABLE schedule2 (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           authorId BIGINT,
                           password VARCHAR(100),
                           task VARCHAR(255) ,
                           createdAt DATETIME,
                           updatedAt DATETIME,
                           FOREIGN KEY (authorId) REFERENCES author(id)
);