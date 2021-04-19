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
CREATE TABLE bill(
	id_bill INTEGER,
    date_bill DATE NOT NULL,
    is_discount_before_deadline BOOLEAN NOT NULL,
    discount_before_deadline DOUBLE,
    discount_coupon INTEGER,
    possible_remarks VARCHAR(20),
    employee INTEGER,
    customer INTEGER,
    CONSTRAINT id_bill_pk PRIMARY KEY(id_bill),
    CONSTRAINT employee_fk
		FOREIGN KEY(employee_fk)
        REFERENCES employee(num_employee),
	CONSTRAINT customer_fk
		FOREIGN KEY(customer_fk)
        REFERENCES customer(num_customer)
);
CREATE TABLE article(
	id_article INTEGER,
    wording VARCHAR(20) NOT NULL,
    price DOUBLE NOT NULL,
    vat DOUBLE NOT NULL,
    aisle INTEGER NOT NULL,
    CONSTRAINT id_article_pk PRIMARY KEY(id_article),
    CONSTRAINT aisle_fk
		FOREIGN KEY(aisle_fk)
        REFERENCES aisle(num_aisle)
);
CREATE TABLE aisle(
	num_aisle INTEGER,
    wording VARCHAR(20) NOT NULL,
    nb_shelves INTEGER NOT NULL,
    CONSTRAINT num_aisle_pk PRIMARY KEY(num_aisle)
);
CREATE TABLE listing(
	quantity INTEGER NOT NULL,
    price DOUBLE NOT NULL,
    bill INTEGER,
    article INTEGER,
    CONSTRAINT bill_fk
		FOREIGN KEY(bill_fk)
        REFERENCES bill(id_bill),
    CONSTRAINT article_fk
		FOREIGN KEY(article_fk)
		REFERENCES article(id_article)
);