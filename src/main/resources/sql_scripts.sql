CREATE DATABASE online_shopping;

CREATE SCHEMA electronics_store;

SET search_path TO electronics_store, public;

DROP TABLE if exists user_address;
DROP TABLE if exists user_payment;
DROP TABLE if exists user_data;

CREATE TABLE user_data
(
    id            BIGSERIAL PRIMARY KEY,
    username      VARCHAR(64)        NOT NULL,
    email         VARCHAR(64) UNIQUE NOT NULL,
    password      VARCHAR(256)       NOT NULL,

    first_name    VARCHAR(64),
    middle_name   VARCHAR(64),
    last_name     VARCHAR(64),
    birth_date    DATE,
    phone_number  VARCHAR(32),
    avatar        VARCHAR(64),

    role          VARCHAR(32),
    registered_at TIMESTAMP
);

-- Many to One
CREATE TABLE user_address
(
    id           BIGSERIAL PRIMARY KEY,
    house_number VARCHAR(16)        NOT NULL,
    street       VARCHAR(128)       NOT NULL,
    city         VARCHAR(128)       NOT NULL,
    postal_code  VARCHAR(16),
    country      VARCHAR(128),
    user_id      BIGINT,
    FOREIGN KEY (user_id) REFERENCES user_data (id) ON DELETE CASCADE
);

-- Many to One
CREATE TABLE user_payment
(
    id           BIGSERIAL PRIMARY KEY,
    payment_type VARCHAR(32),
    provider     VARCHAR(64),
    account_no   VARCHAR(128),
    expire_date  DATE,
    user_id      BIGINT,
    FOREIGN KEY (user_id) REFERENCES user_data (id) ON DELETE CASCADE
);

