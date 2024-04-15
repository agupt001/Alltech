Shop For Home - Final Capstone Project
By - Ankit Gupta

Steps to run the application:
1. Download all the files from microservice and front-end packages.
2. Run all the microservices.
3. Run the database mySQL server, change root username and password if required.
4. Run the SQL commands at the end of this file. Make sure to create an admin for the portal.
5. Install the node modules for angular front-end server.
6. *Important*: Navigate to Bootstrap package in node_modules. At line 2220 add:
    ***.table-striped > tbody > tr:last-child > #td-bg-white {
    --bs-table-color-type: white;
    --bs-table-bg-type: white
}***
7. Run the server and enjoy the application.

_SQL Commands:_

create table user(id integer not null auto_increment, name varchar(255), email varchar(255), 
phone long, username varchar(255), password varchar(255), primary key (id));

create table admin(id integer not null auto_increment, name varchar(255), email varchar(255), 
phone long, username varchar(255), password varchar(255), primary key (id));

create table products(id integer not null auto_increment, name varchar(255), category varchar(255), 
price integer, quantity integer, primary key (id));

create table discounts(id integer not null auto_increment, user_id integer, product_id integer, 
discount_price integer, primary key (id), 
foreign key(user_id) references user(id) ON DELETE CASCADE
, foreign key(product_id) references products(id) ON DELETE CASCADE);

create table shopping_cart(id integer not null auto_increment, user_id integer, product_id integer, 
quantity integer, primary key (id), 
foreign key(user_id) references user(id) ON DELETE CASCADE,
foreign key(product_id) references products(id) ON DELETE CASCADE);

create table wishlist(id integer not null auto_increment, user_id integer, product_id integer, 
primary key (id), 
foreign key(user_id) references user(id) ON DELETE CASCADE,
foreign key(product_id) references products(id) ON DELETE CASCADE);

create table sales(id integer not null auto_increment, user_id integer, product_id integer, product_name varchar(255),
product_category varchar(255), discount_id integer, 
product_price integer, discount integer, final_price integer, quantity integer, 
created_date timestamp DEFAULT current_timestamp, primary key (id));
