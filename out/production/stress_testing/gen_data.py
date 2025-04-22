import csv
import random
import string
import sys

import bcrypt
from faker import Faker

# Скрипт генерирует фальшивые данные пользователей и сохраняет их в CSV-файл для дальнейшего внесения в таблицу users

CSV_FILE = sys.argv[1]
PASSWORD = "securepassword123"  # Дефолтный пароль один на всех
NUM_RECORDS = 500_000

# Генерация bcrypt-хеша пароля
hashed_password = bcrypt.hashpw(PASSWORD.encode('utf-8'), bcrypt.gensalt(prefix=b"2a")).decode('utf-8')

fake = Faker('ru_RU')  # Используем русскую локализацию

print(f"Генерация CSV-файла {CSV_FILE}...")

# Заголовок для CSV
header = ["first_name", "last_name", "birthdate", "city", "biography", "password", "username"]
usernames = set()

hobbies = [
    "чтение", "спорт", "путешествия", "фотография",
    "музыка", "искусство", "кулинария", "программирование",
    "гаджеты", "садоводство", "игры", "кино", "театр",
    "волонтерство", "поэзия", "танцы"
]

with open(CSV_FILE, mode='w', newline='', encoding='utf-8') as output_file:
    writer = csv.writer(output_file)
    writer.writerow(header)

    for _ in range(NUM_RECORDS):
        # Генерация данных
        birthdate = fake.date_of_birth(minimum_age=18, maximum_age=80).isoformat()  # Дата рождения
        city = fake.city()  # Генерация названия города

        full_name = fake.name()  # Генерация полного имени
        name_parts = full_name.split()
        first_name = name_parts[1] if len(name_parts) > 1 else name_parts[0]  # Имя
        last_name = name_parts[0]  # Фамилия

        # Генерация уникального username
        while True:
            username = ''.join(random.choices(string.ascii_letters + string.digits, k=15))
            if username not in usernames:
                usernames.add(username)
                break

        # Генерация случайных увлечений (можно выбрать от 1 до 3)
        selected_hobbies = random.sample(hobbies, random.randint(1, 3))
        biography = " ".join(selected_hobbies)

        writer.writerow([first_name, last_name, birthdate, city, biography, hashed_password, username]
                        )

print(f"Файл {CSV_FILE} создан!")
