#!/bin/bash

# Указываем имя файла для сохранения результатов
output_file="merged_output.txt"

# Проверяем, существует ли файл с таким именем, и если да, то удаляем его
if [ -f "$output_file" ]; then
    rm "$output_file"
fi

# Находим все файлы в текущей директории и во всех подпапках
# и добавляем их содержимое в output_file
find . -type f -name "*" | while read file; do
    echo "$file" >> "$output_file"
    cat "$file" >> "$output_file"
    echo -e "\n-------\n" >> "$output_file"
done

echo "Содержимое всех файлов сохранено в $output_file."
