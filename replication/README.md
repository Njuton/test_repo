# Репликация Postgres 

## Правки в проекте

В качестве источника данных использована реализация [AbstractRoutingDataSource](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/jdbc/datasource/lookup/AbstractRoutingDataSource.html), конфигурация которой представлена в com.example.myapp.config.DataSourceConfig.java, где в зависимости от типа транзакции (READ/WRITE) будет использован либо read-only, либо read-write
```yaml
spring:
  datasource:
    # датасорс необязателен (если указан, то на нём выполняются только read-only транзакции)
    read-only:
      url: ${SPRING_READ_ONLY_DATASOURCE_URL:jdbc:postgresql://localhost:5438/mydatabase}
      username: ${SPRING_READ_ONLY_DATASOURCE_USERNAME:postgres}
      password: ${SPRING_READ_ONLY_DATASOURCE_PASSWORD:postgres}
      driver-class-name: org.postgresql.Driver

    # дефолтный датасорс (если указан read-only, то на read-write только write транзакции, иначе все)
    read-write:
      url: ${SPRING_DATASOURCE_URL:jdbc:postgresql://localhost:5438/mydatabase}
      username: ${SPRING_DATASOURCE_USERNAME:postgres}
      password: ${SPRING_DATASOURCE_PASSWORD:postgres}
      driver-class-name: org.postgresql.Driver
```

**Для проверки работы такого механизма можно:**
1. Запустить докеризованное приложение и 2 независимых инстанса БД Postgres с разными скриптами иинициализации 
./db/init-write.sql и ./db/init-read.sql (некластерная конфигурация postgres). При этом один инстанс используется для read-транзакций, другой - для write:
```shell
docker compose -f app-docker-compose.yaml down -v
docker compose -f app-docker-compose.yaml up --build
```
После успешного старта в логах my_app сервиса будут строчки иницилазции источников:
> Use read-only datasource: jdbc:postgresql://postgres-read:5432/mydatabase 
> 
> Use write datasource: jdbc:postgresql://postgres-write:5432/mydatabase

2. Получить всех пользователей, выполнив REST http://localhost:8888/swagger-ui/index.html#/User%20Management/getAllUsers и убедиться, 
что отсутствуют пользователь с именем "Вацлав Авдеевич", который был добавлен в таблицу users postgres-write скриптом инициализации 
./db/init-write.sql на инстансе postgres-write. Значит приложение читает с postgres-read.
3. Зарегистрировать нового пользователя, выполнив /user/register и получив user_id (WRITE-транзакция). Затем выполнить /user/login (READ-транзакция) и убедиться,
что возвращается ответ 404 (пользователя нет на postgres-read, он на postgres-write).
