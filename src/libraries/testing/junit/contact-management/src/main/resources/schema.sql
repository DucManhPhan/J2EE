CREATE TABLE IF NOT EXISTS customer_contact (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    email VARCHAR(20),
    delivery_address_line1 VARCHAR(50),
    delivery_address_line2 VARCHAR(50),
    delivery_address_city VARCHAR(50),
    delivery_address_state VARCHAR(50),
    delivery_address_zip_code VARCHAR(50)
);