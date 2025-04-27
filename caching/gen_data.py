import psycopg2
import uuid
from faker import Faker
from datetime import datetime

# Скрипт для генерации фэйковых данных: пользователей, друзей и постов друзей

DB_HOST = 'localhost'
DB_NAME = 'mydatabase'
DB_USER = 'postgres'
DB_PASSWORD = 'postgres'
DB_PORT = 5440

fake = Faker()


# Function to create a new user
def create_user(conn, first_name, last_name, username, password, biography, birthdate, city):
    with conn.cursor() as cur:
        user_id = str(uuid.uuid4())
        cur.execute("""
            INSERT INTO users (id, first_name, last_name, username, password, biography, birthdate, city)
            VALUES (%s, %s, %s, %s, %s, %s, %s, %s)
        """, (user_id, first_name, last_name, username, password, biography, birthdate, city))
        return user_id


# Function to create a friendship
def create_friendship(conn, user_id, friend_id):
    with conn.cursor() as cur:
        cur.execute("""
            INSERT INTO user_friends (user_id, friend_id)
            VALUES (%s, %s)
        """, (user_id, friend_id))


# Function to create a post
def create_post(conn, user_id, text):
    with conn.cursor() as cur:
        post_id = str(uuid.uuid4())
        created_at = datetime.now()
        cur.execute("""
            INSERT INTO posts (id, created_at, text, user_id)
            VALUES (%s, %s, %s, %s)
        """, (post_id, created_at, text, user_id))


def main():
    conn = psycopg2.connect(host=DB_HOST, database=DB_NAME, user=DB_USER, password=DB_PASSWORD, port=DB_PORT)

    try:
        # Create three users
        # password = password123
        password = '$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq'
        user1_id = create_user(conn, 'Alice', 'Smith', 'alice_smith', password, 'Hello, I am Alice!', '1990-01-01',
                               'New York')
        user2_id = str(create_user(conn, 'Bob', 'Johnson', 'bob_johnson', password, 'Hi, I am Bob!', '1992-02-02',
                                   'Los Angeles'))
        user3_id = str(
            create_user(conn, 'Charlie', 'Williams', 'charlie_williams', password, 'Greetings, I am Charlie!',
                        '1995-03-03', 'Chicago'))

        # Create friendships
        create_friendship(conn, user1_id, user2_id)
        create_friendship(conn, user1_id, user3_id)

        # Create posts for each friend using Faker
        for user_id in [user2_id, user3_id]:
            for _ in range(2000):  # Create 2000 posts for each friend
                post_text = ' '.join(fake.sentence() for _ in range(5))  # Generate 5 sentences for the post
                create_post(conn, user_id, post_text)

        # Commit the transaction
        conn.commit()

    except Exception as e:
        print(f"An error occurred: {e}")
        conn.rollback()

    finally:
        conn.close()


if __name__ == '__main__':
    main()
