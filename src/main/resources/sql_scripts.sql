CREATE DATABASE online_shopping;

CREATE SCHEMA electronics_store;

SET search_path TO electronics_store, public;

DROP TABLE if exists user_address;
DROP TABLE if exists user_payment;
DROP TABLE if exists user_data;

CREATE TABLE user_data
(
    id            BIGSERIAL PRIMARY KEY,
    username      VARCHAR(64) UNIQUE NOT NULL,
    email         VARCHAR(64) UNIQUE NOT NULL,
    password      VARCHAR(256)       NOT NULL,

    role          VARCHAR(32),
    registered_at TIMESTAMP
);

-- One to One
CREATE TABLE profile (
    id            BIGSERIAL PRIMARY KEY,
    user_id BIGINT UNIQUE NOT NULL,
     first_name    VARCHAR(64),
     middle_name   VARCHAR(64),
     last_name     VARCHAR(64),
     birth_date    DATE,
     phone_number  VARCHAR(32) UNIQUE,
     avatar        VARCHAR(64),
    FOREIGN KEY (user_id) REFERENCES user_data (id)
);

-- Many to One
CREATE TABLE user_address
(
    id           BIGSERIAL PRIMARY KEY,
    contact_phone VARCHAR(32)   UNIQUE NOT NULL,
    house_number VARCHAR(16)        NOT NULL,
    street       VARCHAR(64)       NOT NULL,
    city         VARCHAR(64)       NOT NULL,
    postal_code  VARCHAR(16),
    country      VARCHAR(128),
    user_id      BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user_data (id) ON DELETE CASCADE
);

-- Many to One
CREATE TABLE user_payment
(
    id           BIGSERIAL PRIMARY KEY,
    payment_type VARCHAR(64) NOT NULL ,
    provider     VARCHAR(64) NOT NULL ,
    account_no   VARCHAR(128) NOT NULL UNIQUE,
    expire_date  DATE,
    user_id      BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user_data (id) ON DELETE CASCADE
);


-- Many to Many with user_data
CREATE TABLE role_data (
    id SERIAL PRIMARY KEY,
    name VARCHAR(64) UNIQUE NOT NULL
);

CREATE TABLE user_role (
    user_id BIGINT REFERENCES user_data (id) ON DELETE CASCADE,
    role_id INT REFERENCES role_data (id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, role_id)
);

