DELETE FROM credit_contract WHERE 1=1;
DELETE FROM fact_operation WHERE 1=1;
DELETE FROM type_operation WHERE 1=1;

INSERT INTO type_operation VALUES(0, 'Погашение', 0);
INSERT INTO type_operation VALUES(1, 'Займ', 1);
INSERT INTO type_operation VALUES(2, 'Другой займ', 1);

INSERT INTO fact_operation VALUES(0, 0, 300.7, '2021-10-1');
INSERT INTO fact_operation VALUES(1, 1, 500.5, '2021-10-2');
INSERT INTO fact_operation VALUES(2, 2, 300.3, '2021-10-2');
INSERT INTO fact_operation VALUES(6, 2, 400.3, '2021-10-2');
INSERT INTO fact_operation VALUES(3, 1, 100.1, '2021-10-3');

INSERT INTO fact_operation VALUES(4, 1, 300.6, '2021-10-2');
INSERT INTO fact_operation VALUES(5, 1, 100.2, '2021-10-3');


INSERT INTO credit_contract VALUES(0, '002341', 0);
INSERT INTO credit_contract VALUES(1, '002341', 1);
INSERT INTO credit_contract VALUES(2, '002341', 2);
INSERT INTO credit_contract VALUES(3, '002341', 3);
INSERT INTO credit_contract VALUES(6, '002341', 6);

INSERT INTO credit_contract VALUES(4, '099341', 4);
INSERT INTO credit_contract VALUES(5, '099341', 5);
