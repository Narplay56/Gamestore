Drop database if exists gamestore;

create database gamestore;

use gamestore;
/*
 * Comentario en varias líneas.
 * Compatible con el estándar SQL99
 */
DROP TABLE if exists CUSTOMERS;

DROP TABLE if exists PRODUCTS;

DROP TABLE if exists SHIPMENTS;

DROP TABLE if exists EMPLOYEES;

DROP TABLE if exists ORDERS;

CREATE TABLE EMPLOYEES(
EMP_ID INT PRIMARY KEY,
Full_name varchar(50) Not Null,
Work_shift char(1) Null);

CREATE TABLE CUSTOMERS(
Customer_id INT PRIMARY KEY,
Name varchar(20),
Email varchar(50) Not Null,
Postcode int(5) Not Null,
EMP_ID int,
index(EMP_ID),
foreign key(EMP_ID) references Employees(EMP_ID));
/*
 * Comentario en varias líneas.
 * Compatible con el estándar SQL99
 */
CREATE TABLE PRODUCTS(
Product_id INT PRIMARY KEY,
Product_type varchar(20) Null,
Product_name varchar(50) Not Null,
Price decimal (3,2) Not Null);

CREATE TABLE ORDERS(
Order_id INT PRIMARY KEY,
Customer_id Int,
Index(Customer_id),
foreign key (Customer_id) references Customers(Customer_id),
Product_id Int,
Index(Product_id),
foreign key (Product_id) references PRODUCTS(Product_id));

Create Table Shipments(
Shipment_id int primary key,
company varchar(30) Null,
Order_id int ,
Index(Order_id),
foreign key (Order_id) references ORDERS(Order_id));