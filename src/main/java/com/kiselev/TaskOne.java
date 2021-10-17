package com.kiselev;

import com.kiselev.database.PropertiesLoad;
import com.kiselev.database.CreateTables;
import com.kiselev.database.dbUtils;
import com.kiselev.entity.Accounts;
import com.kiselev.entity.Contracts;
import com.kiselev.entity.Kind_contracts;
import com.kiselev.entity.Types_reg;
import lombok.Getter;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class TaskOne {
    @Getter
    private Connection connection = null;
    @Getter
    private Statement statement = null;

    public TaskOne() throws SQLException, IOException {
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

    private String strFormater(String str, int size){
        StringBuilder stringBuilder = new StringBuilder(str);
        for (int i = (str.length()/2)+1; i <size; i++){
            stringBuilder.insert(0, " ");
        }
        return stringBuilder.toString();
    }

    public void subTaskOne() throws SQLException {
        System.out.println("\nЗадание: 1");
        System.out.println("Вывести список всех договоров в виде (учесть, что ссылка может быть null):\n" +
                "NUM (номер договора)\tACC_NUM (номер счёта), если \"пусто\", выводить \"(не указан)\"\tKIND_NAME (вид договора), если \"пусто\", выводить \"(не указан)\"\nTYPE_NAME (наименование типа), если \"пусто\", выводить \"(не указан)\"\n");

        String sql = "SELECT" +
                     "    contracts.num as c_num," +
                     "    accounts.num as a_num," +
                     "    kind_contracts.name as ks_name," +
                     "    types_reg.name as t_name " +
                     "FROM contracts" +
                     "         LEFT JOIN accounts on contracts.acc_ref = accounts.id" +
                     "         LEFT JOIN kind_contracts on contracts.kind_ref = kind_contracts.id" +
                     "         LEFT JOIN types_reg on kind_contracts.type_ref = types_reg.id";

        System.out.println("Ответ:");
        System.out.println("|NUM (номер договора)|\tACC_NUM (номер счёта)|\tKIND_NAME (вид договора)|\tTYPE_NAME (наименование типа)|");
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()){
            System.out.printf("|%20d|\t", resultSet.getInt("c_num"));
            if(resultSet.getString("a_num") == null)
                System.out.printf("%29s|\t", "(не указан)");
                else
                    System.out.printf("%21s|\t", resultSet.getString("a_num"));

            if(resultSet.getString("ks_name") == null)
                System.out.print(strFormater("(не указан)"+"|", 24));
            else
                System.out.print(strFormater(resultSet.getString("ks_name")+"|", 25));

            if(resultSet.getString("t_name") == null)
                System.out.print(strFormater("(не указан)"+"|", 32));
            else
                System.out.print(strFormater(resultSet.getString("t_name")+"|", 33));

            System.out.println();
        }

    }

    public void fillTablesTestData() throws SQLException {
        dbUtils.clearTable(statement, "contracts");
        dbUtils.clearTable(statement, "types_reg");
        dbUtils.clearTable(statement, "accounts");
        dbUtils.clearTable(statement, "kind_contracts");

        Types_reg typeOne = new Types_reg(0,"электроный");
        Types_reg typeTwo = new Types_reg(1,"бумажный");

        Kind_contracts kind_contractsOne = new Kind_contracts(0,"Контракт аренды", typeOne);
        Kind_contracts kind_contractsTwo = new Kind_contracts(1,"Контракт найма", typeTwo);
        Kind_contracts kind_contractsThree = new Kind_contracts(2,"Контракт найма", null);

        Accounts accountsOne = new Accounts(0, "20565147");
        Accounts accountsTwo = new Accounts(1, "00335147");
        Accounts accountsThree = new Accounts(2, "03434147");
        Accounts accountsFour = new Accounts(3, "03555147");

        ArrayList<Contracts> contractsList = new ArrayList<>();
        contractsList.add(new Contracts(0, "0437099347", accountsOne, kind_contractsOne));
        contractsList.add(new Contracts(1, "0437444347", accountsTwo, kind_contractsOne));
        contractsList.add(new Contracts(2, "0444347", null, kind_contractsTwo));
        contractsList.add(new Contracts(3, "06578907", accountsThree, null));
        contractsList.add(new Contracts(4, "06578436", null, null));
        contractsList.add(new Contracts(5, "02325907", accountsFour, kind_contractsThree));

        for(Contracts c : contractsList){
            c.toDataBase(connection);
        }
            connection.commit();
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }

}
