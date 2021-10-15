SELECT IF(SUM(plan) - SUM(fact) > 0, SUM(plan) - SUM(fact), 0) AS another_dolg FROM(
    SELECT fact_operat.amount AS fact, plan_operat.amount AS plan, COALESCE(fact_operat.date, plan_operat.date) AS date
    FROM credit_contract2
        LEFT JOIN fact_operat ON credit_contract2.fact_op_ref=fact_operat.id
        LEFT JOIN plan_operat ON credit_contract2.plan_op_ref=plan_operat.id
    WHERE credit_contract2.num = '001122') AS summs
WHERE date <= '2021-10-4';