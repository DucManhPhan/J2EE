show databases;

use employee_sample;

CREATE TABLE IF NOT EXISTS employee (
    id int auto_increment not null,
    name varchar(255),
    lastname varchar(255),
    age int,
    primary key (id)
);

INSERT INTO employee(name, lastname, age) VALUES
    ('Thang', 'Cao Ngoc', 27),
    ('Hang', 'Nguyen Thu', 24),
    ('Quan', 'Kieu', 15),
    ('Quan', 'Le', 20),
    ('Tony', 'Buzan', 19),
    ('Trum', 'Donnal', 72),
    ('Bill', 'Clinton', 58),
    ('Zuckerberg', 'Mark', 18)
;