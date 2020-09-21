show databases;

use employee_sample;

CREATE TABLE IF NOT EXISTS employee (
    id int auto_increment not null,
    name varchar(255),
    lastname varchar(255),
    age int,
    primary key (id)
);