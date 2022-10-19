CREATE TABLE IF NOT EXISTS user
(
    id     BIGINT       NOT NULL AUTO_INCREMENT,
    name   VARCHAR(128) NOT NULL,
    status INT      NOT NULL,
    type   INT      NOT NULL
);
