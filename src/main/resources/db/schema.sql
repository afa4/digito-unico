CREATE TABLE APP_USER
(
    id    BIGSERIAL PRIMARY KEY,
    uid   UUID    NOT NULL UNIQUE,
    name  VARCHAR NOT NULL,
    email VARCHAR NOT NULL UNIQUE
);


CREATE TABLE SINGLE_DIGIT
(
    app_user_id  BIGINT,
    integer      VARCHAR NOT NULL,
    repeat_times BIGINT  NOT NULL,
    single_digit int     NOT NULL,

    CONSTRAINT FK_SINGLE_DIGIT_APP_USER FOREIGN KEY (app_user_id)
        REFERENCES APP_USER (id)
);
