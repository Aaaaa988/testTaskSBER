SET @num_account = '002341';
SET @date = '2021-10-5';
SELECT IF( sum(if(type_operation.tag = 1, 1, -1) * amount) > 0,
        sum(if(type_operation.tag = 1, 1, -1) * amount),
        0
        ) as dolg
From fact_operation, credit_contract, type_operation
WHERE credit_contract.num = @num_account
  and credit_contract.fact_op_ref = fact_operation.id
  and fact_operation.date <= @date
  and fact_operation.type_op_ref = type_operation.id