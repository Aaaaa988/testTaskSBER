SET @start_date = '2021-12-4';
SET @end_date = '2021-12-6';
SET @num_account = '001122';
SELECT SUM(procents)  AS sum_procentss
FROM(
    SELECT  IF(@end_date < date2,
                IF(@start_date > date1,
                    ROUND((datediff(@end_date, @start_date) + 1)
                              * (sum_procent/(datediff(date2, date1) + 1)),
                            2),
                    ROUND((datediff(@end_date, date1) + 1)
                              * (sum_procent/(datediff(date2, date1) + 1)),
                            2)),
            IF(@start_date > date1,
                IF(@end_date < date2,
                    ROUND((datediff(@end_date, @start_date) + 1)
                              * (sum_procent/(datediff(date2, date1) + 1)),
                            2),
                    ROUND((datediff(date2, @start_date) + 1)
                              * (sum_procent/(datediff(date2, date1) + 1)),
                            2)),
                sum_procent)
            ) AS procents
    FROM journal_procent
        WHERE num = @num_account
          AND date2 >= @start_date AND date1 <= @end_date
    ) AS procents;