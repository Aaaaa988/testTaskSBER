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
                "(id INTEGER (10) NOT NULL AUTO_INCREMENT, " +
                " num VARCHAR(20) NOT NULL, " +
                " PRIMARY KEY (id))";
        statement.executeUpdate(SQL);

        SQL = "CREATE TABLE IF NOT EXISTS types_reg " +
                "(id INTEGER (10) NOT NULL AUTO_INCREMENT, " +
                " name VARCHAR(20) NOT NULL, " +
                " PRIMARY KEY (id))";
        statement.executeUpdate(SQL);

        SQL = "CREATE TABLE IF NOT EXISTS kind_contracts " +
                "(id INTEGER (10) NOT NULL AUTO_INCREMENT, " +
                " name VARCHAR(30) NOT NULL," +
                " type_ref INTEGER (10)," +
                " PRIMARY KEY (id)," +
                " FOREIGN KEY (type_ref) REFERENCES types_reg(id))";
        statement.executeUpdate(SQL);

        SQL = "CREATE TABLE IF NOT EXISTS contracts " +
                 "(id INTEGER (10) NOT NULL AUTO_INCREMENT, " +
                 " num VARCHAR(20) NOT NULL, " +
                 " acc_ref INTEGER (10)," +
                 " kind_ref INTEGER (10)," +
                 " PRIMARY KEY (id)," +
                 " FOREIGN KEY (acc_ref) REFERENCES accounts(id)," +
                 " FOREIGN KEY (kind_ref) REFERENCES kind_contracts(id))";
        statement.executeUpdate(SQL);

        return connect;
    }

}
