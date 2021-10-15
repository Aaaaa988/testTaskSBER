DELETE FROM credit_contract2 WHERE 1=1;
DELETE FROM fact_operat WHERE 1=1;
DELETE FROM plan_operat WHERE 1=1;

INSERT INTO fact_operat VALUES(0, 300 , '2021-10-1');
INSERT INTO fact_operat VALUES(1, 500 , '2021-10-2');
INSERT INTO fact_operat VALUES(2, 200 , '2021-10-2');
INSERT INTO fact_operat VALUES(3, 100 , '2021-10-3');
INSERT INTO fact_operat VALUES(4, 300 , '2021-10-4');

INSERT INTO fact_operat VALUES(10, 300 , '2021-10-4');


INSERT INTO plan_operat VALUES(0, '2021-10-1', 300);
INSERT INTO plan_operat VALUES(1, '2021-10-2', 500);
INSERT INTO plan_operat VALUES(2, '2021-10-2', 200);
INSERT INTO plan_operat VALUES(3, '2021-10-3', 100);
INSERT INTO plan_operat VALUES(4, '2021-10-4', 1000);

INSERT INTO plan_operat VALUES(10, '2021-10-2', 500);



INSERT INTO credit_contract2 VALUES(0, '001122', 0, 0);
INSERT INTO credit_contract2 VALUES(1, '001122', 1, 1);

INSERT INTO credit_contract2 VALUES(2, '001122', 2, 2);
INSERT INTO credit_contract2 VALUES(3, '001122', 3, 3);

INSERT INTO credit_contract2 VALUES(4, '001122', 4, 4);

INSERT INTO credit_contract2 VALUES(10, '033122', 10, 10);