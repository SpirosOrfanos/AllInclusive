DROP DATABASE if exists shop;

drop table if exists shop.order_item CASCADE ;

drop table if exists shop.orders CASCADE ;

drop table if exists shop.product CASCADE ;

drop table if exists shop.product_details CASCADE ;

CREATE SCHEMA shop ;

create table shop.order_item (
id INT NOT NULL AUTO_INCREMENT, 
price DECIMAL(11,2) NOT NULL DEFAULT 1, 
product_desription varchar(255) not null, 
product_id INT NOT NULL, 
quantity BIGINT(100) NOT NULL DEFAULT 1, 
order_id INT NOT NULL,
PRIMARY KEY (id));

create table shop.orders (
id INT NOT NULL AUTO_INCREMENT, 
order_dt DATETIME NOT NULL, 
status INT NOT NULL, 
primary key (id));

create table shop.product ( 
id INT NOT NULL AUTO_INCREMENT, 
product_type INT NOT NULL,
description varchar(45) not null  DEFAULT '-', 
price DECIMAL(11,2) NOT NULL, 
primary key (id));

create table shop.product_details (
id INT NOT NULL AUTO_INCREMENT, 
description varchar(45) not null  DEFAULT '-', 
detail_info varchar(45) not null  DEFAULT '-', 
product_id INT NOT NULL, 
primary key (id));

ALTER TABLE shop.order_item 
ADD INDEX FK_OITM_idx (order_id ASC) VISIBLE;

ALTER TABLE shop.order_item 
ADD CONSTRAINT FK_OITM
  FOREIGN KEY (order_id)
  REFERENCES shop.orders (id)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE shop.product_details 
ADD INDEX FK_PRDT_idx (product_id ASC) VISIBLE;

ALTER TABLE shop.product_details 
ADD CONSTRAINT FK_PRDT 
  FOREIGN KEY (product_id)
  REFERENCES shop.product (id)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

INSERT INTO shop.product (product_type,description,price) VALUES (1,'Vanilla',15.50);
INSERT INTO shop.product (product_type,description,price) VALUES (1,'Chocolate',11.50);
INSERT INTO shop.product (product_type,description,price) VALUES (1,'Strawberry',12.50);
INSERT INTO shop.product (product_type,description,price) VALUES (1,'Apple',12.50);


INSERT INTO shop.product_details(description,detail_info,product_id)VALUES('Color','White',1);
INSERT INTO shop.product_details(description,detail_info,product_id)VALUES('kCal','5000',1);
INSERT INTO shop.product_details(description,detail_info,product_id)VALUES('weight','500gr',1);

INSERT INTO shop.product_details(description,detail_info,product_id)VALUES('Color','Brown',2);
INSERT INTO shop.product_details(description,detail_info,product_id)VALUES('kCal','4000',2);
INSERT INTO shop.product_details(description,detail_info,product_id)VALUES('weight','450gr',2);

INSERT INTO shop.product_details(description,detail_info,product_id)VALUES('Color','Pink',3);
INSERT INTO shop.product_details(description,detail_info,product_id)VALUES('kCal','1000',3);
INSERT INTO shop.product_details(description,detail_info,product_id)VALUES('weight','250gr',3);

INSERT INTO shop.product_details(description,detail_info,product_id)VALUES('Color','Green',4);
INSERT INTO shop.product_details(description,detail_info,product_id)VALUES('kCal','2000',4);
INSERT INTO shop.product_details(description,detail_info,product_id)VALUES('weight','4000gr',4);