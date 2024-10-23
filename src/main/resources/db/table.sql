CREATE TABLE IF NOT EXISTS INVOICE (
	id int GENERATED ALWAYS AS IDENTITY,
	amount DOUBLE PRECISION,
	paid_amount DOUBLE PRECISION,
	due_date varchar(20),
	status varchar(15)
);