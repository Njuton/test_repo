# Работа с InMemory СУБД

## Изменения в проекте
### Добавлен модуль работы с диалогами с использованием Redis-а
В [RedisDialogController.java](../src/main/java/com/example/myapp/rest/RedisDialogController.java) добавлены новые REST-методы **POST /redis/dialog/{receiver_id}/send** для отправки сообщений от пользователя пользователю и
**GET /redis/dialog/{receiver_id}/list** для получения списка сообщений по аналогии с имеющимися /dialog/* методами.

Теперь в методах
- **sendMessage** Для хранения сообщений используется список Redis, где ключ формируется на основе идентификаторов отправителя и получателя; Сообщение сериализуется в формат JSON
и добавлеяется в конец списка через использование Lua-скрипта.
- **getDialog** Использует Lua-скрипт дял получения всех элементов списка по ключу на основе идентификаторов отправителя и получателя;

## Выполнение задания

### Профиль нагрузочного и ПО
Для проведения нагрузочного тестирования решил использовать JMeter. 

В БД Postgres было создано 1000 пользователей скриптом генерации данных (gen_data.py).
Затем user_id всех пользователей были занесены в groovy-скрипт в Jmeter, который каждый новый вызов возвращает UUID случайного пользователя: randomUser.

#### Тестирование POST **/dialog/{receiver_id}/send
1. Кол-во потоков 10 шт
2. Ограничение по времени: 60 сек
3. От одного пользователя (токен передаю в хэдэре) выполняем отправку произвольного текста длиной 100 символов рандомному user_id, который возвращает groovy-скрипт

#### Тестирование GET **/dialog/{receiver_id}/list
1. Кол-во потоков 10 шт
2. Ограничение по времени: 30 сек
3. От одного пользователя (токен передаю в хэдэре) выполняем получение диалогов с произвольным пользователем, идентификатор которого возвращает groovy-скрипт

### Анализ результатов

1. Запросы на send

**Postgres**
![img.png](results/send_dialogs.png)
**Redis**
![img.png](results/send_dialogs_redis.png)

Пропускная способность для Redis выше (396 зап/сек против 286 зап/сек), средняя latency ниже на 10 мс;

2. Запросы на get

**Postgres**
![img.png](results/get_data.png)
**Redis**
![img.png](results/get_data_redis.png)

Пропускная способность для Redis выше (299 зап/сек против 254 зап/сек), средняя latency ниже на 6 мс;

## Проверка работоспособности 

1. Поднимаем Redis, postgres (поднимается на бэкапе) и приложение
```shell
docker compose build --no-cache
docker compose -f docker-compose.yaml down --volumes 
docker compose -f docker-compose.yaml up 
```
2. Переходим в swagger-UI по http://localhost:8888/swagger-ui.html
3. Вводим токен авторизаци для alice_smith в окошко по кнопочке Authorize, расположенное справа вверху страницы
```shell
eyJhbGciOiJIUzUxMiJ9.eyJ1c2VySWQiOiI5MDJlY2FiMC0zYWE0LTQ5Y2ItYmRlMi1kMjdmNGU1ZGFjYzAiLCJzdWIiOiI5MDJlY2FiMC0zYWE0LTQ5Y2ItYmRlMi1kMjdmNGU1ZGFjYzAiLCJpYXQiOjE3NDgxOTg4OTQsImV4cCI6MTc0ODgwMzY5NH0.mM_F7WtX3FCAE7Uczszrr_Gfi6sm5mIbLLhi2tm7f8e7r20exKimYTU98UUNbmtohw8L5h4cR8Li7GjbuukGBQ
```
4. Выполняем POST /redis/dialog/{user_id}/send с user_id 27c82f29-2e8f-46c0-ae9c-9a144f4157f7 (пользователь sosajennifer)
5. Выполняем GET /redis/dialog/{user_id}/list с тем же user_id 

![img.png](imgs/send_result.png)