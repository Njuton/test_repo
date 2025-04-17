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

CREATE TABLE public.schema_history (
    installed_rank integer NOT NULL PRIMARY KEY,
    version character varying(50),
    description character varying(200) NOT NULL,
    type character varying(20) NOT NULL,
    script character varying(1000) NOT NULL,
    checksum integer,
    installed_by character varying(100) NOT NULL,
    installed_on timestamp without time zone DEFAULT now() NOT NULL,
    execution_time integer NOT NULL,
    success boolean NOT NULL
);

INSERT INTO public.schema_history (installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success)
VALUES (1, '1', 'init schema', 'SQL', 'V1__init_schema.sql', 1317146632, 'postgres', NOW(), 7, true);

INSERT INTO users (first_name, last_name, birthdate, city, biography, password, username) VALUES
('Орест', 'Овчинников', '1987-06-29', 'инстанс-2 с. Белый Яр (Томск.)', 'садоводство кулинария', '$2a$12$qNhq8hs4/.HSJ1hilMbLAOwMaaDCI0lXq7QfbpBf.COnTG5fVjF6e', 'QeCygCsR6ewJJbD');

INSERT INTO users (first_name, last_name, birthdate, city, biography, password, username) VALUES
('Самсон', 'Петухов', '1973-01-14', 'инстанс-2 клх Пушкин', 'поэзия', '$2a$12$qNhq8hs4/.HSJ1hilMbLAOwMaaDCI0lXq7QfbpBf.COnTG5fVjF6e', 'rIU59grpqC65Zs2');

INSERT INTO users (first_name, last_name, birthdate, city, biography, password, username) VALUES
('Григорий', 'Ильин', '1983-08-23', 'инстанс-2 г. Гремячинск (Перм.)', 'чтение искусство кулинария', '$2a$12$qNhq8hs4/.HSJ1hilMbLAOwMaaDCI0lXq7QfbpBf.COnTG5fVjF6e', 't2RXtDzcVZPbJuH');

INSERT INTO users (first_name, last_name, birthdate, city, biography, password, username) VALUES
('Адамович', 'Тимофей', '1953-01-05', 'инстанс-2 п. Фатеж', 'гаджеты', '$2a$12$qNhq8hs4/.HSJ1hilMbLAOwMaaDCI0lXq7QfbpBf.COnTG5fVjF6e', 'rkIaRqmLihPaBJs');
