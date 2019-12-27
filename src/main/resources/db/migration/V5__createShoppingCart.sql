DROP TABLE IF EXISTS shopping_cart;

create table shopping_cart
(
  id                 bigint auto_increment
    primary key,
  is_deleted         bit         not null,
  customer_id        bigint      null,
  product_id         bigint      null,
  created_date       datetime default CURRENT_TIMESTAMP null,
  last_modified_date datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
);

ALTER table shopping_cart add CONSTRAINT uq_Item UNIQUE (customer_id,product_id);