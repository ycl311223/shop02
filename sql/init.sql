--类似于php中的牵引文件
drop database shop02 if exists shop02;
create database shop02;
grant all on shop02.* to 'root'@'localhost' identified by 'root';
use shop02;
create table t_user(
	id int(11) primary key auto_increment,
	username varchar(100),
	password varchar(100),
	nickname varchar(100),
	type int(5)
	
);
create table t_address(
	id int(11) primary key auto_increment,
	name varchar(100),
	phone varchar(100),
	postcode varchar(100),
	user_id int(11),
	constraint foreign key(user_id) references t_user(id)
);
create table t_orders(
	id int(11) primary key auto_increment,
	buy_date datetime,
	pay_date datetime,
	confirm_date datetime,
	user_id int(11),
	address_id int(11),
	status int(5),
	price double,
	constraint foreign key(user_id) references t_user(id),
	constraint foreign key(address_id) references t_address(id)
	
);
create table t_category(
	id int(11) primary key auto_increment,
	name varchar(100)
);
create table t_product(
	id int(11) primary key auto_increment,
	name varchar(100),
	price double,
	category_id int(11),
	intro text,
	status int(2),
	img varchar(100),
	stock int(11),
	constraint foreign key(category_id) references t_category(id)
	
);
create table t_car_product(
	id int(11) primary key auto_increment,
	number int(11),
	price double,
	o_id int(11),
	p_id int(11)
);
create table t_product_orders(
	id int(11) primary key auto_increment,
	product_id int(11),
	orders_id int(11),
	constraint foreign key(product_id) references t_product(id),
	constraint foreign key(orders_id) references t_orders(id)
	
);

