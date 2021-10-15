DROP TABLE IF EXISTS credit_contract2;
DROP TABLE IF EXISTS plan_operat;
DROP TABLE IF EXISTS fact_operat;


CREATE TABLE plan_operat(
    id INTEGER (10) NOT NULL,
    date DATE NOT NULL,
    amount DOUBLE NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE fact_operat(
    id INTEGER (10) NOT NULL,
    amount DOUBLE NOT NULL,
    date DATE NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE credit_contract2(
    id INTEGER (10) NOT NULL,
    num VARCHAR(20) NOT NULL,
    fact_op_ref INTEGER(10),
    plan_op_ref INTEGER(10),
    PRIMARY KEY (id),
    FOREIGN KEY (fact_op_ref) REFERENCES fact_operat(id) ON DELETE CASCADE,
    FOREIGN KEY (plan_op_ref) REFERENCES plan_operat(id) ON DELETE CASCADE
);