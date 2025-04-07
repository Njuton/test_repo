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

INSERT INTO users (first_name, last_name, birthdate, city, biography, password, username) VALUES
('Авдеевич', 'Вацлав', '1999-07-24', 'инстанс-1 с. Красноселькуп', 'танцы', '$2a$12$qNhq8hs4/.HSJ1hilMbLAOwMaaDCI0lXq7QfbpBf.COnTG5fVjF6e', '2oCB2hFrGxg3a2n');

INSERT INTO users (first_name, last_name, birthdate, city, biography, password, username) VALUES
('Аггей', 'Орехов', '1979-12-21', 'инстанс-1 клх Нефтекамск', 'гаджеты', '$2a$12$qNhq8hs4/.HSJ1hilMbLAOwMaaDCI0lXq7QfbpBf.COnTG5fVjF6e', 'gWNPeuwBQ56No6O');

INSERT INTO users (first_name, last_name, birthdate, city, biography, password, username) VALUES
('Зоя', 'Журавлева', '1967-05-14', 'инстанс-1 г. Крымск', 'волонтерство искусство', '$2a$12$qNhq8hs4/.HSJ1hilMbLAOwMaaDCI0lXq7QfbpBf.COnTG5fVjF6e', 'wSvB3Xz0BSETpQJ');

INSERT INTO users (first_name, last_name, birthdate, city, biography, password, username) VALUES
('Софон', 'Стрелков', '1954-08-11', 'инстанс-1 клх Ноглики', 'путешествия фотография', '$2a$12$qNhq8hs4/.HSJ1hilMbLAOwMaaDCI0lXq7QfbpBf.COnTG5fVjF6e', 'YglSECH5rYKvJQ3');

INSERT INTO users (first_name, last_name, birthdate, city, biography, password, username) VALUES
('Игоревна', 'Полина', '1997-11-23', 'инстанс-1 д. Энгельс', 'спорт волонтерство искусство', '$2a$12$qNhq8hs4/.HSJ1hilMbLAOwMaaDCI0lXq7QfbpBf.COnTG5fVjF6e', 'F5D6A14yL0cwB9M');

INSERT INTO users (first_name, last_name, birthdate, city, biography, password, username) VALUES
('Гурьевич', 'Фотий', '1980-08-24', 'инстанс-1 ст. Казань', 'гаджеты кулинария', '$2a$12$qNhq8hs4/.HSJ1hilMbLAOwMaaDCI0lXq7QfbpBf.COnTG5fVjF6e', 'zAMQ07eTrx1TRKd');

INSERT INTO users (first_name, last_name, birthdate, city, biography, password, username) VALUES
('Богдановна', 'Лора', '1966-05-31', 'инстанс-1 с. Устюжна', 'садоводство поэзия спорт', '$2a$12$qNhq8hs4/.HSJ1hilMbLAOwMaaDCI0lXq7QfbpBf.COnTG5fVjF6e', 's0MFPev0pfFasov');

INSERT INTO users (first_name, last_name, birthdate, city, biography, password, username) VALUES
('Ольга', 'Колобова', '1979-09-20', 'инстанс-1 г. Енисейск', 'волонтерство музыка гаджеты', '$2a$12$qNhq8hs4/.HSJ1hilMbLAOwMaaDCI0lXq7QfbpBf.COnTG5fVjF6e', 'kI7C7YNNTWAa7QL');

INSERT INTO users (first_name, last_name, birthdate, city, biography, password, username) VALUES
('Кира', 'Третьякова', '1997-12-20', 'инстанс-1 п. Губаха', 'кулинария', '$2a$12$qNhq8hs4/.HSJ1hilMbLAOwMaaDCI0lXq7QfbpBf.COnTG5fVjF6e', 'CUILAzxKtj9FF5j');
