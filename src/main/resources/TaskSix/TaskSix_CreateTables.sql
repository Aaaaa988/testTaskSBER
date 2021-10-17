DROP TABLE credit_contract;
DROP TABLE setting;

CREATE TABLE credit_contract(
    id NUMBER,
    num VARCHAR2(20),
    class VARCHAR2(20),
    filial VARCHAR2(20),
    fintool VARCHAR2(20),
    PRIMARY KEY (id)
);

CREATE TABLE setting(
    id NUMBER,
    pid NUMBER,
    class_id VARCHAR2(20),
    filial VARCHAR2(20),
    fintool VARCHAR2(20),
    value VARCHAR2(100),
    PRIMARY KEY (id)
);