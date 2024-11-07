insert into book
values (nextval('book_id_seq'), 1928, 'Мастер и Маргарита'),
       (nextval('book_id_seq'), 1865, 'Преступление и наказание'),
       (nextval('book_id_seq'), 1880, 'Маленький принц'),
       (nextval('book_id_seq'), 1925, 'Собачье сердце'),
       (nextval('book_id_seq'), 1923, 'Белая гвардия'),
       (nextval('book_id_seq'), 1925, 'Зойкина квартира'),
       (nextval('book_id_seq'), 1867, 'Идиот'),
       (nextval('book_id_seq'), 1997, 'Гарри Поттер и философский камень'),
       (nextval('book_id_seq'), 2003, 'Гарри Поттер и орден феникса'),
       (nextval('book_id_seq'), 2007, 'Гарри Поттер и дары смерти'),
       (nextval('book_id_seq'), 1999, 'Гарри Поттер и узник Азкабана');

insert into author
values (nextval('author_id_seq'), '1891-05-15', 'Михаил', 'Булгаков'),
       (nextval('author_id_seq'), '1821-11-11', 'Фёдор', 'Достоевский'),
       (nextval('author_id_seq'), '1900-06-29', 'Антуан', 'Сент-Экзюпери'),
       (nextval('author_id_seq'), '1965-07-31', 'Джоан', 'Роулинг');

insert into book_author
values (1, 1),
       (2, 2),
       (3, 3),
       (4, 1),
       (5, 1),
       (6, 1),
       (7, 2),
       (8, 4),
       (9, 4),
       (10, 4),
       (11, 4);

insert into client
values (nextval('client_id_seq'), '2001-05-14', 'Иван', 'MALE', 'Иванов', '+78005553535'),
       (nextval('client_id_seq'), '2004-07-16', 'Анна', 'FEMALE', 'Иоановна', '89225553535'),
       (nextval('client_id_seq'), '1990-01-12', 'Катя', 'FEMALE', 'Романова', '8 (800) 777-35-35'),
       (nextval('client_id_seq'), '1982-12-29', 'Вася', 'MALE', 'Пупкин', null);

insert into operation
values (nextval('operation_id_seq'), '2024-01-01', 'TAKE', 1, 2),
       (nextval('operation_id_seq'), '2024-01-15', 'RETURN', 1, 2),
       (nextval('operation_id_seq'), '2024-09-23', 'TAKE', 2, 1),
       (nextval('operation_id_seq'), '2024-10-15', 'RETURN', 2, 1),
       (nextval('operation_id_seq'), '2024-01-15', 'TAKE', 6, 2),
       (nextval('operation_id_seq'), '2024-02-02', 'RETURN', 6, 2),
       (nextval('operation_id_seq'), '2024-02-18', 'TAKE', 9, 2),
       (nextval('operation_id_seq'), '2024-03-22', 'RETURN', 9, 2),
       (nextval('operation_id_seq'), '2024-05-01', 'TAKE', 3, 3),
       (nextval('operation_id_seq'), '2024-05-09', 'RETURN', 3, 3),
       (nextval('operation_id_seq'), '2024-11-01', 'TAKE', 4, 1),
       (nextval('operation_id_seq'), '2024-07-07', 'TAKE', 1, 4),
       (nextval('operation_id_seq'), '2024-07-07', 'TAKE', 7, 4),
       (nextval('operation_id_seq'), '2024-08-01', 'TAKE', 11, 3);

-- admin / admin user
insert into "user"
values (1, 'admin', '$2a$12$dLhNO41lXCJWmuGMX9UMseG.3tNTk41firOzVIwO7p12dMpwbHpuG');


