package com.kiselev.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class dbUtils {
    /** Функция для получения значения последнего первичного ключа таблицы
      *
     */
    public static int getMaxId(Statement statement, String tableName) {


        String sql = "SELECT MAX(id) FROM " + tableName;
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            return resultSet.next()?resultSet.getInt("MAX(id)") : 0;
        }catch (SQLException e){
            System.err.println(tableName+" - такой таблицы не существует");
            return -1;
        }
    }

    public static int clearTable(Statement statement, String tableName) {
        try {

            statement.execute("DELETE FROM " + tableName + " WHERE 1=1");
            System.out.println("Произошло удаление содержимого таблицы - " + tableName);
            return 0;
        } catch (SQLException e) {
            System.out.println("lolololo");
            return -1;
        }
    }
}
