create table if not exists product
(
    id           bigint  not null auto_increment,
    product_id   varchar(255),
    product_name varchar(255),
    stock        integer not null,
    unit_price   integer not null,
    primary key (id)
) engine = InnoDB;