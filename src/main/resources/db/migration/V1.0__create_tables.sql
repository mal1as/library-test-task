create table author
(
    id         bigserial primary key,
    birth_date date not null,
    first_name varchar(32) not null,
    last_name  varchar(32) not null
);

create table book
(
    id               bigserial primary key,
    publication_year integer,
    title            varchar(64) not null
);

create table book_author
(
    book_id   bigint not null references book(id),
    author_id bigint not null references author(id)
);

create table client
(
    id           bigserial primary key,
    birth_date   date not null,
    first_name   varchar(32) not null,
    gender       varchar(16) not null,
    last_name    varchar(32) not null,
    phone_number varchar(32)
);

create table operation
(
    id             bigserial primary key,
    operation_date timestamp not null default now(),
    operation_type varchar(16) not null,
    book_id        bigint not null references book(id),
    client_id      bigint not null references client(id)
);