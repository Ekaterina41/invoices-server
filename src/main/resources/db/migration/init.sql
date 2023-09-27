-- Security
CREATE SEQUENCE user_data_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

CREATE TABLE user_data (
	id int4 NOT NULL,
	username varchar NULL,
	password varchar NULL,
	CONSTRAINT user_pk PRIMARY KEY (id)
);

-- Invoice
CREATE SEQUENCE invoice_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

CREATE TABLE invoice (
	id int4 NOT NULL,
	"number" int4 NULL,
	"date" date NULL,
	"period" date NULL,
	days_off_amount int2 NULL,
	work_days_amount int2 NULL,
	total int4 NULL,
	vat int4 NULL,
	total_to_pay int4 NULL,
	CONSTRAINT invoice_pk PRIMARY KEY (id)
);

-- invoice_entry_seq definition

CREATE SEQUENCE invoice_entry_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

-- Invoice entry
CREATE TABLE invoice_entry (
	id int4 NOT NULL,
	service_name varchar NULL,
	quantity_hours int2 NULL,
	"cost" int4 NULL,
	invoice_id int4 NOT NULL,
	CONSTRAINT invoice_entry_pk PRIMARY KEY (id),
	CONSTRAINT invoice_entry_fk FOREIGN KEY (invoice_id) REFERENCES invoice(id)
);
