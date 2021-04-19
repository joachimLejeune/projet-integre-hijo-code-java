CREATE TABLE employee(
	num_employee INTEGER,
    first_name VARCHAR(20) NOT NULL,
    last_name VARCHAR(25) NOT NULL,
    birth_date DATE NOT NULL,
    address VARCHAR(50) NOT NULL,
    phone_number INTEGER NOT NULL,
    email VARCHAR(50),
    CONSTRAINT num_employee_pk PRIMARY KEY(num_employee),
    CONSTRAINT email_employee UNIQUE(email)
);
CREATE TABLE customer(
	num_customer INTEGER,
    first_name VARCHAR(20) NOT NULL,
    last_name VARCHAR(25) NOT NULL,
    birth_date DATE NOT NULL,
    address VARCHAR(50) NOT NULL,
    phone_number INTEGER NOT NULL,
    email VARCHAR(50),
    CONSTRAINT num_customer_pk PRIMARY KEY(num_customer),
    CONSTRAINT email_customer UNIQUE(email)
);
CREATE TABLE bill();
CREATE TABLE article();
CREATE TABLE aisle();
CREATE TABLE listing();