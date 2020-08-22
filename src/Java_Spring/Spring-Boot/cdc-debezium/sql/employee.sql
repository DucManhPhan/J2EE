create table employee (
    id bigint not null primary key,
    first_name varchar(50),
    last_name varchar(50),
    email varchar(50),
    phone_number varchar(20),
    emp_desg varchar(10)
);

# Create data for employee table
insert into employee(id, first_name, last_name, email, phone_number, emp_desg)
values
(1, 'quan', 'kieu manh', 'quankm@gmail.com', '0989438978', 'AMP'),
(2, 'thang', 'cao ngoc', 'thangcn@gmail.com', '05236584257', 'BQP'),
(3, 'thang', 'dao ngoc', 'thangdn@gmail.com', '05254858425', 'BQP')
;