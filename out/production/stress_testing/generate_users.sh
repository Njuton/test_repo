#!/bin/bash

source ./venv/bin/activate

# дефолтные значения докер-композа
CONTAINER_NAME="postgres_container"
DB_NAME="mydatabase"
DB_USER="postgres"
TABLE_NAME="users"

# выходной файл
DIRECTORY='./generated'
CSV_FILE="$DIRECTORY/output_file.csv"

if [ ! -d "$DIRECTORY" ]; then
    mkdir -p "$DIRECTORY"
fi

python gen_data.py $CSV_FILE

if [ ! -f "$CSV_FILE" ]; then
    echo "Ошибка: Файл $CSV_FILE не найден!"
    exit 1
fi

echo "Копирование CSV в контейнер $CONTAINER_NAME..."
docker cp "$CSV_FILE" "$CONTAINER_NAME:/users.csv"

# === Создание SQL-файла с `\COPY` для быстроты запросов ===
SQL_COMMANDS="$DIRECTORY/import.sql"
echo "TRUNCATE TABLE $TABLE_NAME;" > "$SQL_COMMANDS"
echo "\COPY $TABLE_NAME (first_name, last_name, birthdate, city, biography, password, username) FROM '/users.csv' DELIMITER ',' CSV HEADER;" >> "$SQL_COMMANDS"

docker cp "$SQL_COMMANDS" "$CONTAINER_NAME:/import.sql"
echo "Импорт данных в PostgreSQL через COPY ..."
docker exec -i "$CONTAINER_NAME" psql -U "$DB_USER" -d "$DB_NAME" -f /import.sql

echo "Готово!"
