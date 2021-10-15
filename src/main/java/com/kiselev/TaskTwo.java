package com.kiselev;

import com.kiselev.database.PropertiesLoad;
import com.kiselev.database.CreateTables;
import com.kiselev.database.dbUtils;
import com.kiselev.entity.*;
import lombok.Getter;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class TaskTwo {

    @Getter
    private Connection connection = null;
    @Getter
    private Statement statement = null;

    public TaskTwo() throws SQLException, IOException {
        PropertiesLoad prop = PropertiesLoad.getInstance();
        connection = DriverManager.getConnection(prop.getHost(), prop.getLogin(), prop.getPassword());
        statement = connection.createStatement();

        CreateTables createTables = new CreateTables();

        connection = createTables.ConfigureBase(connection, statement, prop);
        connection.setAutoCommit(false);
        connection.commit();
        statement = connection.createStatement();

        if (!connection.isClosed()) {
            System.out.println("Соединение с БД установлено");
        }

    }

    public void subTaskTwo(String acc_num, String date) throws SQLException {
        System.out.println("\nЗадание: 2");
        System.out.println("Написать SQL-запрос, получающий на указанную дату остаток.");
        //Строго с текущим днем

        String sql = "" +
                "SELECT sum(if(movements.operation = 0, 1, -1) * amount) as rest from movements, accounts " +
                "where  date <= '"+date+"' and accounts.num = '"+acc_num+"' and movements.id_acc = accounts.id";

        ResultSet resultSet = statement.executeQuery(sql);

        System.out.println("Ответ:");
        if(resultSet.next()) {
            if(resultSet.getDouble("rest") > 0){
                System.out.println("Остаток по счету " + acc_num + " за дату " + date);
                System.out.println(resultSet.getDouble("rest"));
            }else{
                System.out.println("Остаток отрицательный!");
            }
        }

    }

    public void subTaskThree(String acc_num, String date1, String date2) throws SQLException {
        System.out.println("\nЗадание: 3");
        System.out.println("Написать SQL-запрос, получающий за указанный период оборот по списанию и оборот по зачислению.");

        if (date1.compareTo(date2) > 0){
            String temp = date1;
            date1 = date2;
            date2 = temp;
        }

        String sql = "" +
                "select" +
                "    (select IFNULL(sum(amount), 0)" +
                "    from movements, accounts" +
                "    where operation=0 " +
                "      and date BETWEEN '"+date1+"' and '"+date2+"'" +
                "      and accounts.num = '"+acc_num+"'" +
                "      and movements.id_acc = accounts.id" +
                "    ) as income," +
                "    (select IFNULL(sum(amount), 0)" +
                "     from movements," +
                "          accounts" +
                "     where operation = 1" +
                "       and date BETWEEN '"+date1+"' and '"+date2+"'" +
                "       and accounts.num = '"+acc_num+"'" +
                "       and movements.id_acc = accounts.id" +
                "    ) as outcome";

        ResultSet resultSet = statement.executeQuery(sql);

        System.out.println("Ответ:");
        if(resultSet.next()) {
            System.out.println("Оборот по счету " + acc_num + " за период " + date1 + " - " + date2);
            System.out.println("Приход = " + resultSet.getDouble("income") + " Расход = "+ resultSet.getDouble("outcome"));
        }

    }

    public void subTaskFour(double rate, String acc_num, String date1, String date2) throws SQLException {
        System.out.println("\nЗадание: 4");
        System.out.println("Написать процедуру расчёта процентов (входные параметры: ставка, дата начала и окончания периода расчёта).");

        if (date1.compareTo(date2) > 0){
            String temp = date1;
            date1 = date2;
            date2 = temp;
        }

        String sql = "CALL get_procent("+rate+", '"+acc_num+"','"+date1+"','"+date2+"');";

        ResultSet resultSet = statement.executeQuery(sql);
        System.out.println("Ответ:");
        if(resultSet.next()) {
            System.out.println("Процент по остаткам счета " + acc_num + "\nза период с " + date1 + " по " + date2 + ".\nПри ставке равной "+rate+"% составил:");
            System.out.println(String.format("%.2f", resultSet.getDouble("procent"))+ "р");
        }

    }

    public void fillTablesTestData() throws SQLException {

        dbUtils.clearTable(statement, "accounts");
        dbUtils.clearTable(statement, "movements");

        Accounts accountsOne = new Accounts(0, "20565147");
        Accounts accountsTwo = new Accounts(1, "00335147");
        Accounts accountsThree = new Accounts(2, "03434147");
        Accounts accountsFour = new Accounts(3, "03555147");

        ArrayList<Movements> movementsList = new ArrayList<>();

        movementsList.add(new Movements(1, accountsOne, "2021-10-1", 500, 0));
        movementsList.add(new Movements(2, accountsOne, "2021-10-1", 235, 1));
        movementsList.add(new Movements(3, accountsOne, "2021-10-3", 50, 0));
        movementsList.add(new Movements(4, accountsOne, "2021-10-3", 543, 0));
        movementsList.add(new Movements(5, accountsOne, "2021-10-3", 300, 1));
        movementsList.add(new Movements(6, accountsOne, "2021-10-4", 111, 1));


        for(Movements m : movementsList){
            m.toDataBase(connection);
        }
        connection.commit();
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }

}
