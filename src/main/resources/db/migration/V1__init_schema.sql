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

ALTER TABLE users ALTER COLUMN id SET DEFAULT gen_random_uuid();

CREATE TABLE user_friends (
    user_id uuid not null,
    friend_id uuid not null,
    primary key (user_id, friend_id));

ALTER TABLE user_friends ADD CONSTRAINT fk_user_friends_friend FOREIGN KEY (friend_id) REFERENCES users;
ALTER TABLE user_friends ADD CONSTRAINT fk_user_friends_user FOREIGN KEY (user_id) REFERENCES users;

create table posts (
    id uuid not null,
    created_at timestamp(6) not null,
    text varchar(65535) not null,
    user_id uuid not null,
    primary key (id));

alter table posts add constraint fk_post_user foreign key (user_id) references users;