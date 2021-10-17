package com.kiselev;

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

            /*TaskTwo taskTwo = new TaskTwo();
            taskTwo.fillTablesTestData();
            taskTwo.subTaskTwo("20565147", "2021-10-1");
            taskTwo.subTaskThree("20565147", "2021-10-1","2021-10-4");
            taskTwo.subTaskFour(3,"20565147", "2021-10-1","2026-10-5");*/

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

    }
}
