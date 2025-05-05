-- Шардируемая таблица сообщений
create table messages
    (sender_id uuid not null,
    id uuid not null,
    created_at timestamp(6) not null,
    receiver_id uuid not null,
    text varchar(65535) not null,
    primary key (id, sender_id));

-- Шардирование таблицы по sender_id
SELECT create_distributed_table('messages', 'sender_id');

-- Создание индекса для ускорения запросов
CREATE INDEX idx_receiver_id ON messages(receiver_id);