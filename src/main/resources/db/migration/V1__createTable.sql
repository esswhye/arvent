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