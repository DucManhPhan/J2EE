
CREATE TABLE employee_audit (
   id INT GENERATED ALWAYS AS IDENTITY,
   employee_id INT NOT NULL,
   old_row_data TEXT,
   new_row_data TEXT,
   dml_type VARCHAR(20) not null,
   dml_timestamp TIMESTAMP NOT NULL
);
