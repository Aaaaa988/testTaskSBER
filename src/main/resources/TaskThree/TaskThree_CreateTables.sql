DROP TABLE IF EXISTS credit_contract;
DROP TABLE IF EXISTS fact_operation;
DROP TABLE IF EXISTS type_operation;



CREATE TABLE type_operation(
    id INTEGER (10) NOT NULL,
    name VARCHAR(20) NOT NULL,
    tag BOOL NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE fact_operation(
    id INTEGER (10) NOT NULL,
    type_op_ref INTEGER(10) NOT NULL,
    amount DOUBLE NOT NULL,
    date DATE NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (type_op_ref) REFERENCES type_operation(id) ON DELETE CASCADE
);

CREATE TABLE credit_contract(
    id INTEGER (10) NOT NULL,
    num VARCHAR(20) NOT NULL,
    fact_op_ref INTEGER(10) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (fact_op_ref) REFERENCES fact_operation(id) ON DELETE CASCADE
);