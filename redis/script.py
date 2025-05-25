import json

with open('users.json', 'r', encoding='utf-8') as file:
    data = json.load(file)

usernames = [user['id'] for user in data]

# Формируем строку списка
usernames_string = json.dumps(usernames)

# Выводим результат
print(usernames_string)
