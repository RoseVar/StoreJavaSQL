-- Create user for local access.
CREATE USER 'usrstoreapp'@'localhost' IDENTIFIED BY 'pswstore';

-- Create database.
CREATE DATABASE dbstoreapp
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;

-- Grant permissions.
GRANT SELECT, INSERT, UPDATE, DELETE ON dbstoreapp.* TO 'usrstoreapp'@'localhost';

-- Use database.
USE dbstoreapp;

-- Create table 'countries'
CREATE TABLE `products` (
`code` varchar(40) NOT NULL UNIQUE,
`description` varchar(40),
`price` double default 0.0,
`stock` int default 0,
PRIMARY KEY (`code`)
) ENGINE=InnoDB;

-- Data insertion.
INSERT INTO `products` (`code`, `description`, `price`, `stock`) 
VALUES ("C01", "desc01", 101.0, 11), 
("C02", "desc02", 102.0, 12), 
("C03", "desc03", 103.0, 13),
("C04", "desc04", 104.0, 14),
("C05", "desc05", 105.0, 15),
("C06", "desc06", 106.0, 16);
