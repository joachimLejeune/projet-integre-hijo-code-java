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
    date_bill DATE NOT NULL CONSTRAINT date_bill_cst CHECK(date_bill >= SYSDATE()),
    is_discount_before_deadline BOOLEAN NOT NULL,
    discount_before_deadline DOUBLE,
    discount_coupon INTEGER,
    possible_remarks VARCHAR(20),
    employee INTEGER,
    customer INTEGER,
    CONSTRAINT id_bill_pk PRIMARY KEY(id_bill),
    CONSTRAINT employee_fk
		FOREIGN KEY(employee)
        REFERENCES employee(num_employee),
	CONSTRAINT customer_fk
		FOREIGN KEY(customer)
        REFERENCES customer(num_customer)
);
CREATE TABLE aisle(
	num_aisle INTEGER,
    wording VARCHAR(20) NOT NULL,
    nb_shelves INTEGER NOT NULL CONSTRAINT nb_shelves_cst CHECK(nb_shelves > 0),
    CONSTRAINT num_aisle_pk PRIMARY KEY(num_aisle)
);
CREATE TABLE article(
	id_article INTEGER,
    wording VARCHAR(50) NOT NULL,
    price DOUBLE NOT NULL CONSTRAINT price_article_cst CHECK(price > 0),
    vat DOUBLE NOT NULL CONSTRAINT vat_article_cst CHECK(vat IN(0, 0.06, 0.21)),
    aisle INTEGER NOT NULL,
    CONSTRAINT id_article_pk PRIMARY KEY(id_article),
    CONSTRAINT aisle_fk
		FOREIGN KEY(aisle)
        REFERENCES aisle(num_aisle)
);
CREATE TABLE listing(
	quantity INTEGER NOT NULL CONSTRAINT quantity_cst CHECK(quantity > 0),
    price DOUBLE NOT NULL CONSTRAINT price_listing_cst CHECK(price > 0),
    bill INTEGER,
    article INTEGER,
    CONSTRAINT bill_fk
		FOREIGN KEY(bill)
        REFERENCES bill(id_bill),
    CONSTRAINT article_fk
		FOREIGN KEY(article)
		REFERENCES article(id_article)
);