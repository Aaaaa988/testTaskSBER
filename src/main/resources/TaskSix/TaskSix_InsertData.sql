DELETE FROM credit_contract WHERE 1=1;
DELETE FROM setting WHERE 1=1;

INSERT INTO SETTING VALUES (1,null, null, null, null, 'Общее');
INSERT INTO SETTING VALUES (2, 1, 'PRIVATE', null, null, 'Для частных лиц');
INSERT INTO SETTING VALUES (3, 2, 'PRIVATE', '001', null, 'Для частных лиц 001 филиала');
INSERT INTO SETTING VALUES (4, 2, 'PRIVATE', '002', null, 'Для частных лиц 002 филиала');
INSERT INTO SETTING VALUES (5, 3, 'PRIVATE', '001', 'RUR', 'Для частных лиц 001 филиала в рублях');
INSERT INTO SETTING VALUES (6, 3, 'PRIVATE', '001', 'USD', 'Для частных лиц 001 филиала в долларах США');

INSERT INTO CREDIT_CONTRACT VALUES (0, '001122', 'PRIVATE', '001','RUR' );