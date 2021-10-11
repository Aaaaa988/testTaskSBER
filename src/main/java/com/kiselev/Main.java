package com.kiselev;

import com.kiselev.database.DataBaseJDBC;
import com.kiselev.database.PropertiesLoad;
import java.io.IOException;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            PropertiesLoad prop = new PropertiesLoad();
            connection = DriverManager.getConnection(prop.getHost(), prop.getLogin(), prop.getPassword());
            Statement statement = connection.createStatement();
            DataBaseJDBC Base = new DataBaseJDBC();
            connection = Base.CreateBase(connection, statement, prop); // создание базы данных и таблиц
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            connection.commit(); /// сохранение изменений

            connection.close();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

    }
}
