DROP TABLE IF EXISTS journal_procent;

CREATE TABLE journal_procent(
    id INTEGER (10) NOT NULL,
    num VARCHAR(20) NOT NULL,
    date1 DATE NOT NULL,
    date2 DATE NOT NULL,
    sum_procent DOUBLE NOT NULL,
    PRIMARY KEY (id)
);