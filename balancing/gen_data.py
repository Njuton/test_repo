import uuid

import psycopg2
from faker import Faker

# Скрипт для генерации фэйковых данных: пользователей, друзей, постов друзей и сообщений

# основной инстанс БД
DB_HOST = 'localhost'
DB_NAME = 'mydatabase'
DB_USER = 'postgres'
DB_PASSWORD = 'postgres'
DB_PORT = 5440

fake = Faker()


def create_user(conn, first_name, last_name, username, password, biography, birthdate, city):
    with conn.cursor() as cur:
        user_id = str(uuid.uuid4())
        cur.execute("""
            INSERT INTO users (id, first_name, last_name, username, password, biography, birthdate, city)
            VALUES (%s, %s, %s, %s, %s, %s, %s, %s)
        """, (user_id, first_name, last_name, username, password, biography, birthdate, city))
        return user_id


def main():
    conn = psycopg2.connect(host=DB_HOST, database=DB_NAME, user=DB_USER, password=DB_PASSWORD, port=DB_PORT)

    try:
        # password = password123
        password = '$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq'

        # Создание фиксированных пользователей
        fixed_users = [
            ('Alice', 'Smith', 'alice_smith', 'Hello, I am Alice!', '1990-01-01', 'New York'),
            ('Bob', 'Johnson', 'bob_johnson', 'Hi, I am Bob!', '1992-02-02', 'Los Angeles'),
            ('Charlie', 'Williams', 'charlie_williams', 'Greetings, I am Charlie!', '1995-03-03', 'Chicago')
        ]

        for first_name, last_name, username, biography, birthdate, city in fixed_users:
            user_id = create_user(conn, first_name, last_name, username, password, biography, birthdate, city)

        # Генерация 100 случайных пользователей
        for _ in range(100):
            first_name = fake.first_name()
            last_name = fake.last_name()
            username = fake.user_name()
            biography = fake.sentence()
            birthdate = '1990-01-01'
            city = fake.city()
            create_user(conn, first_name, last_name, username, password, biography, birthdate, city)

        conn.commit()

    except Exception as e:
        print(f"An error occurred: {e}")
        conn.rollback()

    finally:
        conn.close()


if __name__ == '__main__':
    main()
