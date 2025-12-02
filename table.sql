CREATE DATABASE travel_reservations;
USE travel_reservations;
CREATE TABLE customer (
  CustomerId INT PRIMARY KEY AUTO_INCREMENT,
  FirstName VARCHAR(50),
  LastName VARCHAR(50),
  Address VARCHAR(100),
  City VARCHAR(50),
  State VARCHAR(20),
  ZipCode INT,
  Email VARCHAR(100),
  Rating INT
);
