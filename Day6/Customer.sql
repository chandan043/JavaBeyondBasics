CREATE TABLE Customer (
    customer_id BIGINT PRIMARY KEY,
    date_of_birth VARCHAR(20) NOT NULL,
    email_address VARCHAR(50) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    id_type VARCHAR(20) NOT NULL,
    address VARCHAR(100) NOT NULL,
    state VARCHAR(50) NOT NULL
);
