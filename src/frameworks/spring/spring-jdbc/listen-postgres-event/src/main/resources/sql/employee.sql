DROP TABLE IF EXISTS employee;

CREATE TABLE employee(
   id INT GENERATED ALWAYS AS IDENTITY,
   first_name VARCHAR(40) NOT NULL,
   last_name VARCHAR(40) NOT NULL,
   age smallint,

   PRIMARY KEY(id)
);
