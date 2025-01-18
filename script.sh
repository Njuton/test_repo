#!/bin/bash

# Проверяем, что находимся в git-репозитории
if ! git rev-parse --is-inside-work-tree > /dev/null 2>&1; then
    echo "Ошибка: текущая директория не является git-репозиторием."
    exit 1
fi

# Путь к директории (по умолчанию текущая директория)
INPUT_DIR="${1:-.}"
# Имя выходного файла (по умолчанию merged_output.txt)
OUTPUT_FILE="${2:-merged_output.txt}"

# Создаём (или очищаем) выходной файл
> "$OUTPUT_FILE"

# Рекурсивно обходим файлы, исключая те, которые заигнорированы в .gitignore
while IFS= read -r FILE; do
    echo "Добавляю файл: $FILE"
    echo "=== $FILE ===" >> "$OUTPUT_FILE"  # Добавляем полное имя файла в выходной файл
    cat "$FILE" >> "$OUTPUT_FILE"          # Добавляем содержимое файла
    echo -e "\n" >> "$OUTPUT_FILE"         # Добавляем перенос строки между файлами
done < <(git ls-files --others --exclude-standard --cached --full-name "$INPUT_DIR")

echo "Все файлы объединены в: $OUTPUT_FILE"
