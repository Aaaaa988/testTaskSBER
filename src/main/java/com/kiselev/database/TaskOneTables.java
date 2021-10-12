package com.kiselev.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TaskOneTables {
    public Connection ConfigureBase(Connection connection, Statement statement, PropertiesLoad prop) throws SQLException {
        Connection connect = connection;
        statement.executeUpdate("CREATE DATABASE IF NOT EXISTS " + prop.getBaseName());

        connection.close();
        connect = DriverManager.getConnection(prop.getHost() + prop.getBaseName(), prop.getLogin(), prop.getPassword());
        statement = connect.createStatement();
        String SQL = "";

        SQL = "CREATE TABLE IF NOT EXISTS accounts " +
                "(id INTEGER (10) NOT NULL, " +
                " num VARCHAR(20) NOT NULL UNIQUE, " +
                " PRIMARY KEY (id))";
        statement.executeUpdate(SQL);

        SQL = "CREATE TABLE IF NOT EXISTS types_reg " +
                "(id INTEGER (10) NOT NULL, " +
                " name VARCHAR(20) NOT NULL UNIQUE, " +
                " PRIMARY KEY (id))";
        statement.executeUpdate(SQL);

        SQL = "CREATE TABLE IF NOT EXISTS kind_contracts " +
                "(id INTEGER (10) NOT NULL, " +
                " name VARCHAR(30) NOT NULL," +
                " type_ref INTEGER (10)," +
                " PRIMARY KEY (id)," +
                " FOREIGN KEY (type_ref) REFERENCES types_reg(id) ON DELETE CASCADE)";
        statement.executeUpdate(SQL);

        SQL = "CREATE TABLE IF NOT EXISTS contracts " +
                 "(id INTEGER (10) NOT NULL, " +
                 " num VARCHAR(20) NOT NULL UNIQUE, " +
                 " acc_ref INTEGER (10)," +
                 " kind_ref INTEGER (10)," +
                 " PRIMARY KEY (id)," +
                 " FOREIGN KEY (acc_ref) REFERENCES accounts(id) ON DELETE CASCADE," +
                 " FOREIGN KEY (kind_ref) REFERENCES kind_contracts(id) ON DELETE CASCADE)";
        statement.executeUpdate(SQL);

        return connect;
    }

}
