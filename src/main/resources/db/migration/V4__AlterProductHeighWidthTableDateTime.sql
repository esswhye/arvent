ALTER TABLE product_height_width modify
  created_date datetime default CURRENT_TIMESTAMP null;

ALTER TABLE product_height_width modify
  last_modified_date datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP;