package com.kiselev;

import com.kiselev.database.PropertiesLoad;
import com.kiselev.database.TaskOneTables;
import com.kiselev.database.dbUtils;
import com.kiselev.entity.Accounts;
import com.kiselev.entity.Contracts;
import com.kiselev.entity.Kind_contracts;
import com.kiselev.entity.Types_reg;
import lombok.Getter;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class TaskOne {
    @Getter
    private Connection connection = null;
    @Getter
    private Statement statement = null;

    public TaskOne() throws SQLException, IOException {
        PropertiesLoad prop = PropertiesLoad.getInstance();
        connection = DriverManager.getConnection(prop.getHost(), prop.getLogin(), prop.getPassword());
        statement = connection.createStatement();

        TaskOneTables taskOneTables = new TaskOneTables();

        connection = taskOneTables.ConfigureBase(connection, statement, prop);
        connection.setAutoCommit(false);
        connection.commit();
        statement = connection.createStatement();

        if (!connection.isClosed()) {
            System.out.println("Соединение с БД установлено");
        }

    }

    public void subTaskOne() throws SQLException {
        String sql;
        sql = "SELECT" +
                "    contracts.num as c_num," +
                "    accounts.num as a_num," +
                "    kind_contracts.name as ks_name," +
                "    types_reg.name as t_name " +
                "FROM contracts" +
                "         LEFT JOIN accounts on contracts.acc_ref = accounts.id" +
                "         LEFT JOIN kind_contracts on contracts.kind_ref = kind_contracts.id" +
                "         LEFT JOIN types_reg on kind_contracts.type_ref = types_reg.id";

        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()){
            System.out.println(
                    resultSet.getInt("c_num") +" "+
                    resultSet.getString("a_num") +" "+
                            resultSet.getString("ks_name") +" "+
                            resultSet.getString("t_name"));
        }

    }

    public void fillTablesTestData() throws SQLException {
        dbUtils.clearTable(statement, "contracts");
        dbUtils.clearTable(statement, "types_reg");
        dbUtils.clearTable(statement, "accounts");
        dbUtils.clearTable(statement, "kind_contracts");



        Types_reg typeOne = new Types_reg(dbUtils.getMaxId(statement, "types_reg"),"электроный");
        Types_reg typeTwo = new Types_reg(dbUtils.getMaxId(statement, "types_reg"),"бумажный");
        Kind_contracts kind_contractsOne = new Kind_contracts(dbUtils.getMaxId(statement, "kind_contracts"),"Contract arend", typeOne);


        ArrayList<Contracts> contractsList = new ArrayList<>();
        contractsList.add(new Contracts(
                0,
                "0437099347",
                new Accounts(dbUtils.getMaxId(statement, "accounts"), "20565147"),
                kind_contractsOne));

        contractsList.add(new Contracts(
                1001,
                "0437444347",
                new Accounts(dbUtils.getMaxId(statement, "accounts")+1, "00335147"),
                kind_contractsOne));

        contractsList.add(new Contracts(
                131,
                "0444347",
                null,
                kind_contractsOne));




            contractsList.get(0).toDataBase(connection);
        contractsList.get(1).toDataBase(connection);
        contractsList.get(2).toDataBase(connection);

            connection.commit();
    }


    public void closeConnection() throws SQLException {
        connection.close();
    }


}
