--CREATE DATABASE hw_db;

CREATE SCHEMA IF NOT EXISTS hw;

CREATE TABLE IF NOT EXISTS hw.test
(
    id int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name varchar(100) NOT NULL
);

-- можно добавить индексы для FK полей - для перфа join

CREATE TABLE IF NOT EXISTS hw.question
(
    id int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    test_id int REFERENCES hw.test(id), -- ON DELETE CASCADE
    text varchar(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS hw.answer
(
    id int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    question_id int REFERENCES hw.question(id),
    text varchar(100) NOT NULL,
    is_correct boolean NOT NULL
);