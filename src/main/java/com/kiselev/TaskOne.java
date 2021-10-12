package com.kiselev;

import com.kiselev.database.PropertiesLoad;
import com.kiselev.database.TaskOneTables;
import com.kiselev.database.dbUtils;
import com.kiselev.entity.Accounts;
import com.kiselev.entity.Contracts;

import java.io.IOException;
import java.sql.*;

public class TaskOne {
    private Connection connection = null;
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
        sql = "SELECT * FROM accounts";

        ResultSet resultSet = statement.executeQuery(sql);

        System.out.println("Retrieving data from database...");
        System.out.println("\nAccounts:");
        Accounts accounts = new Accounts();
        while (resultSet.next()) {
            accounts.setId(resultSet.getInt("id"));
            accounts.setNum(resultSet.getString("num"));


            System.out.println("================");
            System.out.println(accounts.toString());


        }
        //System.out.println(dbUtils.getMaxId(statement, "accounts"));

        /*Contracts contracts = new Contracts();
        contracts.setId(2);
        contracts.setNum("453434");
        System.out.println(contracts.toString());*/

    }

    public void closeConnection() throws SQLException {
        connection.close();
    }


}
