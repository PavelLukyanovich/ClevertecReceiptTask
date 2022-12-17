create table public.cards
(
    id          bigint not null
        primary key,
    card_number varchar(5)
        unique,
    discount    real
);

alter table public.cards
    owner to postgres;

create table public.products
(
    id                   bigint not null
        primary key,
    product_amount       integer,
    has_product_discount boolean,
    product_title        varchar(10)
);

alter table public.products
    owner to postgres;

create table public.receipt
(
    id                     bigint not null
        primary key,
    cashier_id             varchar(3),
    date                   timestamp(6),
    discount               integer,
    amount_of_product      integer,
    price_of_product       integer,
    time                   bytea,
    receipt_title          varchar(255),
    total_price_of_product integer,
    total_price_of_receipt integer
);

alter table public.receipt
    owner to postgres;