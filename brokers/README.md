# Работа с ws и брокером сообщений

## Изменения в проекте
### Отложенная материализация ленты 
Теперь при создании поста через POST /post/create выполняется отправка dto поста в брокер сообщений в обменник DIRECT_EXCHANGE по
routingKey: groupQueue_1 или groupQueue_2, или groupQueue_3. Конкретный routingKey определяется по хэшу идентификатора отправителя. Логика
реализована в [PostController.java](../src/main/java/com/example/myapp/rest/PostController.java). На каждую из очередей настроен обработчик,
который вызывает логику отправки уведомления о новом посте через ws-канал. При этом использовался Spring WebSocket-сервер.

### Уведомление пользователя о новых постах друзей по web-socket

Реализован [WebSocketService.java](../src/main/java/com/example/myapp/ws/WebSocketService.java), где определён
метод notifyFriends уведомления друзей пользователя о новом посте. Средствами Spring выполняется отправка сообщения
в топик **/user/{sessionId}/post/feed/posted**, где sessionId определяется по username Principal из авторизации пользователя по токену,
который передается на сторону сервера в момент установки web-socket соединения. Клиенту необходимо только подписаться 
на **/user/post/feed/posted**, вся остальная логика работы с сессиями предоставляется из коробки StompClient.

![img.png](./imgs/diagram.png)

### Написана html-страница для подписки на web-socket и отображение результата

При заходе на главную страницу приложения *http://localhost:8888* 

![img.png](./imgs/ws-page.png)

## Проверка работоспособности и анализ результатов

1. Поднимаем RabbitMQ, postgres (поднимается на бэкапе) и приложение
```shell
docker compose -f docker-compose.yml down 
docker compose -f docker-compose.yml up 
```

2. В бэкапе есть 
- пользователь с **username=alice_smith** и **id=cae6ecd9-0278-4f7a-a30e-8f9802efb1c6**
- пользователь с **username=charlie_williams** (друг alice_smith) и **id=c2a89151-0741-4b3e-b538-0dc125ae9d63**

Для alice_smith получаем токен доступа, зайдя на http://localhost:8888/swagger-ui.html и выполнив метод POST /login (пароль password123).
Это

`eyJhbGciOiJIUzUxMiJ9.eyJ1c2VySWQiOiJjYWU2ZWNkOS0wMjc4LTRmN2EtYTMwZS04Zjk4MDJlZmIxYzYiLCJzdWIiOiJjYWU2ZWNkOS0wMjc4LTRmN2EtYTMwZS04Zjk4MDJlZmIxYzYiLCJpYXQiOjE3NDc3NzM4MTksImV4cCI6MTc0ODM3ODYxOX0.amJwft6kHhXGb1SpR_6zPdi8lreVPVProLovd141T7aZfJwi-Wu5yaRnLfgq2FeWkpUFwsA0FOzFgmRY6EQRpQ`

Для alice_smith получаем токен доступа, зайдя на http://localhost:8888/swagger-ui.html и выполнив метод POST /login (пароль password123).
Это

`eyJhbGciOiJIUzUxMiJ9.eyJ1c2VySWQiOiJjMmE4OTE1MS0wNzQxLTRiM2UtYjUzOC0wZGMxMjVhZTlkNjMiLCJzdWIiOiJjMmE4OTE1MS0wNzQxLTRiM2UtYjUzOC0wZGMxMjVhZTlkNjMiLCJpYXQiOjE3NDc3NzM5OTksImV4cCI6MTc0ODM3ODc5OX0.0B69lPk5CtNWmOoyY00j7LAFcoChBhvAM6gLTCV-JnKuBoQFoshuOyeOvS3CsVJ6G12q0f0QFHl8PUFO-ombtw`

3. Открываем в разных вкладках (анонимная и неанонимная) страницу с http://localhost:8888
и на одной вводим токен alice_smith и жмём "Connect", на другой - charlie_williams и жмём "Connect"

4. На странице сваггера нажимаем Authorize и вводим токен для charlie_williams
5. Выполняем от имени второго пользователя запрос **POST /post/create**

![img.png](imgs/result_ws.png)

Видим, что WS-соединение успешно установлено с веб-сервером и отправленный пост от второго пользователя дошёл первому пользователю (т.к. он друг),
но не дошел второму (т.к. отправляем только друзьям)

### Линейное масштабирование сервиса

1. Разворачиваем кластер RabbitMQ, чтобы распределить нагрузку по нескольким узлам.
Для этого на каждом из узлов поднимаем RabbitMQ и на мастере выполняем
```shell
rabbitmqctl stop_app
rabbitmqctl join_cluster rabbit@nodeName
rabbitmqctl start_app
```
Это присоединит инстанс rabbit@nodeName к кластеру.

2. Линейно увеличиваем кол-во очередей и разносим их по разным узлам в кластере без зеркалирования. Для этого для каждого узла кластера на стороне приложения
настраиваем свою ConnectionFactory к RabbitMQ и RabbitTemplate. 

3. Линейно увеличиваем кол-во экземляров WebSocket-сервера (по сути экземпляр приложения) и используем балансировщик нагрузки (например, Nginx или HAProxy) чтобы распредеить
соединения по серверам и поддерживать sticky sessions (удержание соединения одним и тем же сервером)

