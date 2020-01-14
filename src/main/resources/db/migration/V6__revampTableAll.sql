select * from products;
DROP TABLE IF EXISTS shopping_cart;
DROP TABLE IF EXISTS order_items;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS shopping_cart;
DROP TABLE IF EXISTS product_height_width;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS customers;

create table customers
(
  id          bigint       not null AUTO_INCREMENT primary key,
  user_name   varchar(255) not null,
  address     varchar(255) not null,
  email       varchar(255) not null,
  first_name  varchar(255) not null,
  last_name   varchar(255) not null,
  password    varchar(255) not null,
  postal_code varchar(255) not null,
  created_date       DATETIME              DEFAULT CURRENT_TIMESTAMP  ,
  last_modified_date DATETIME              DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

create table products
(
  id bigint not null AUTO_INCREMENT primary key,
  product_name varchar(255) not null,
  product_brand varchar (255),
  product_price float  not null,
  product_discount float ,
  available_quantity           int        ,
  block_quantity           int        ,
  product_image_link varchar (255),
  created_date       DATETIME              DEFAULT CURRENT_TIMESTAMP  ,
  last_modified_date DATETIME              DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

create table product_height_width
(
  product_height     int         not null,
  product_width      int         not null,
  product_id         bigint      not null,
  created_date       DATETIME              DEFAULT CURRENT_TIMESTAMP  ,
  last_modified_date DATETIME              DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  constraint unique_product_image
  foreign key (product_id) references products (id)
);

ALTER TABLE customers ADD CONSTRAINT U_userName UNIQUE(user_name);

create table orders
(
  id                 bigint auto_increment
  primary key,
  current_status     varchar(255) not null,
  total_price        double       not null,
  customer_id        bigint       null,
  created_date       DATETIME              DEFAULT CURRENT_TIMESTAMP  ,
  last_modified_date DATETIME              DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  constraint unique_customer_order
  foreign key (customer_id) references customers (id)
);

create table order_items
(
  id                      bigint auto_increment
    primary key,
  purchased_product_price double      null,
  quantity                int         not null,
  sub_total               double      not null,
  order_id                bigint      not null,
  product_id              bigint      null,
  created_date       DATETIME              DEFAULT CURRENT_TIMESTAMP  ,
  last_modified_date DATETIME              DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

  constraint unique_item_order
    foreign key (order_id) references orders (id),
  constraint unique_product_order
    foreign key (product_id) references products (id)
);

create table shopping_cart
(
  id                 bigint auto_increment
  primary key,
  quantity           int         not null,
  customer_id        bigint      null,
  product_id         bigint      null,
  created_date       DATETIME              DEFAULT CURRENT_TIMESTAMP  ,
  last_modified_date DATETIME              DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  constraint unique_item
  unique (customer_id, product_id),
  constraint unique_product_cart
  foreign key (product_id) references products (id),
  constraint unique_customer_cart
  foreign key (customer_id) references customers (id)
);