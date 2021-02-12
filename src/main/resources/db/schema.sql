CREATE TABLE APP_USER
(
    id    BIGSERIAL PRIMARY KEY,
    uid   UUID UNIQUE DEFAULT random_uuid(),
    name  VARCHAR(250) NOT NULL,
    email VARCHAR(250) NOT NULL UNIQUE
);