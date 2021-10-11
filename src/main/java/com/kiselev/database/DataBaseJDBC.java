package com.kiselev.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseJDBC {
    public Connection CreateBase(Connection connection, Statement statement, PropertiesLoad Pload) throws SQLException {
        Connection connect = connection;
        statement.executeUpdate("CREATE DATABASE IF NOT EXISTS " + Pload.getBaseName()); //создание БД




        return connect;
    }

}
