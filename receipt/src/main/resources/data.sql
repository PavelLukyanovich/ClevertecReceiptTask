create table if not exists public.cards
(
    id          bigint not null primary key,
    card_number varchar(5) unique,
    discount    real
);

create table if not exists public.products
(
    id           bigint not null primary key,
    price        numeric,
    has_discount boolean,
    title        varchar(50)
);

create table if not exists public.receipts
(
    id                 bigint not null primary key,
    creation_date_time timestamp(6),
    json               varchar(255)
);

CREATE SEQUENCE IF NOT EXISTS receipts_seq;

copy cards FROM '/tmp/cards.csv' DELIMITER ',';
copy products FROM '/tmp/products.csv' DELIMITER ',';