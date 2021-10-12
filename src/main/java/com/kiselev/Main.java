package com.kiselev;

import com.kiselev.database.TaskOneTables;
import com.kiselev.database.PropertiesLoad;
import java.io.IOException;
import java.sql.*;

public class Main {
    public static void main(String[] args) {

        try {
            TaskOne taskOne = new TaskOne();
            taskOne.subTaskOne();
            taskOne.closeConnection();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

    }
}
