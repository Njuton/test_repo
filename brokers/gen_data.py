import psycopg2
import uuid
from faker import Faker
from datetime import datetime
import random

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


def create_friendship(conn, user_id, friend_id):
    with conn.cursor() as cur:
        cur.execute("""
            INSERT INTO user_friends (user_id, friend_id)
            VALUES (%s, %s)
        """, (user_id, friend_id))


def create_post(conn, user_id, text):
    with conn.cursor() as cur:
        post_id = str(uuid.uuid4())
        created_at = datetime.now()
        cur.execute("""
            INSERT INTO posts (id, created_at, text, user_id)
            VALUES (%s, %s, %s, %s)
        """, (post_id, created_at, text, user_id))


def create_message(conn, sender_id, receiver_id, text):
    with conn.cursor() as cur:
        message_id = str(uuid.uuid4())
        created_at = datetime.now()
        cur.execute("""
            INSERT INTO messages (id, created_at, sender_id, receiver_id, text)
            VALUES (%s, %s, %s, %s, %s)
        """, (message_id, created_at, sender_id, receiver_id, text))


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

        users = []
        for first_name, last_name, username, biography, birthdate, city in fixed_users:
            user_id = create_user(conn, first_name, last_name, username, password, biography, birthdate, city)
            users.append(user_id)

        # Генерация 100 случайных пользователей
        for _ in range(100):
            first_name = fake.first_name()
            last_name = fake.last_name()
            username = fake.user_name()
            biography = fake.sentence()
            birthdate = '1990-01-01'
            city = fake.city()
            user_id = create_user(conn, first_name, last_name, username, password, biography, birthdate, city)
            users.append(user_id)

        # Создаем мапу друзей
        friendships = {user: set() for user in users}

        # Делаем пользователей друзьями (каждый с 20-ью). При этом если A дружит с B, то и B дружит с A
        for user_id in users:
            possible_friends = [u for u in users if u != user_id]
            while len(friendships[user_id]) < 20:
                friend_id = random.choice(possible_friends)
                if friend_id not in friendships[user_id]:
                    create_friendship(conn, user_id, friend_id)
                    create_friendship(conn, friend_id, user_id)
                    friendships[user_id].add(friend_id)
                    friendships[friend_id].add(user_id)

        # Каждый пользователь генерит 10 постов из 5 предложений
        for user_id in users:
            for _ in range(10):
                post_text = ' '.join(fake.sentence() for _ in range(5))
                create_post(conn, user_id, post_text)

        # Каждый пользователь отправляет всем своим друзьям 20 сообщений
        for user_id in users:
            for _ in range(20):
                sender_id = user_id
                for receiver_id in friendships[user_id]:
                    message_text = fake.sentence()
                    create_message(conn, sender_id, receiver_id, message_text)

        conn.commit()

    except Exception as e:
        print(f"An error occurred: {e}")
        conn.rollback()

    finally:
        conn.close()


if __name__ == '__main__':
    main()
