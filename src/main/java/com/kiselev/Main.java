package com.kiselev;

import com.kiselev.database.TaskOneTables;
import com.kiselev.database.PropertiesLoad;
import com.kiselev.database.dbUtils;

import java.io.IOException;
import java.sql.*;

public class Main {
    public static void main(String[] args) {

        try {
            TaskOne taskOne = new TaskOne();
            //dbUtils.clearTable(taskOne.getStatement(), "contracts");
            taskOne.fillTablesTestData();
            taskOne.subTaskOne();
            taskOne.closeConnection();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

    }
}
