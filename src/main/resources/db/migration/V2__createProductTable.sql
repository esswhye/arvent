create table products
(
  id bigint not null AUTO_INCREMENT primary key,
  product_name varchar(255) not null,
  product_brand varchar (255),
  product_price float  not null,
  product_discount float ,
  product_imagelink varchar (255),
  created_date       DATETIME              DEFAULT CURRENT_TIMESTAMP  ,
  last_modified_date DATETIME              DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
/*
create table product_height_width
(
  id bigint not null primary key,
  product_Width int not null,
  product_Height int not null,
  created_date       DATETIME              DEFAULT CURRENT_TIMESTAMP  ,
  last_modified_date DATETIME              DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT `FK_id`
  FOREIGN KEY (`id`) REFERENCES `products` (`id`)

);
*/
create table product_height_width
(
  created_date       datetime(6) null,
  last_modified_date datetime(6) null,
  product_height     int         not null,
  product_width      int         not null,
  product_id         bigint      not null
    primary key,
  constraint FK_id
    foreign key (product_id) references products (id)
);