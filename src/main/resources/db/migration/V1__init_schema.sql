CREATE TABLE users (
    id UUID NOT NULL PRIMARY KEY,
    biography VARCHAR(255),
    birthdate DATE,
    city VARCHAR(255),
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    password VARCHAR(255),
    username VARCHAR(255) NOT NULL UNIQUE
);

