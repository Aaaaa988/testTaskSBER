DROP PROCEDURE IF EXISTS `get_procent`;

DELIMITER $$
CREATE PROCEDURE `get_procent` (
    IN rate DOUBLE,
    IN num INT,
    IN date1 DATE,
    IN date2 DATE
)
BEGIN
    DECLARE rest INT;
    DECLARE  InTargetDate DATE;
    DECLARE  InStopDate DATE;
    DECLARE  daysInYear INT;
    DECLARE procent DOUBLE;
    SET rest = 0;
    SET procent = 0;

    SET InTargetDate = date1;
    SET InStopDate = date2;

    WHILE InTargetDate<=InStopDate DO
        # Получение количества дней в году
        IF (MOD(YEAR(InTargetDate), 400) = 0) THEN
            SET daysInYear = 366;
        ELSE
            IF (MOD(YEAR(InTargetDate), 4) = 0 AND MOD(YEAR(InTargetDate), 100) != 0) THEN
                SET daysInYear = 366;
            ELSE
                SET daysInYear = 365;
            END IF;
        END IF;

        # Получение остатка  - остаток получаем за текущий день не включительно 'date < InTargetDate'
        # тоесть получаем остаток словно измерение происходит в 00:00 нового дня, как требуется по условию
        SELECT sum(if(operation = 0, 1, -1) * amount) as ostatok from movements, accounts
        where  date < InTargetDate and accounts.num = num and movements.id_acc = accounts.id into rest;

        # Если остаток положителен, то происходит высчет процента
        IF(rest > 0) THEN
            SET procent = procent + (rest * (rate/100))/ daysInYear;
        END IF;
        # Прибавление дня
        SET InTargetDate = DATE_ADD(InTargetDate, INTERVAL 1 DAY);
    END WHILE;
    # Вывод процентов за расчетный период
    SELECT procent;
END $$

DELIMITER ;

-- execute the stored procedure
CALL get_procent(3, '20565147','2021-10-1','2026-10-5');

-- display result
#select @result;