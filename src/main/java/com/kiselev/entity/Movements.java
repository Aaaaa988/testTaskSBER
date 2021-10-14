package com.kiselev.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@NoArgsConstructor
@AllArgsConstructor
public class Movements {
    @Getter @Setter
    private int id;
    @Getter @Setter
    private Accounts id_acc;
    @Getter @Setter
    private String date;
    @Getter @Setter
    private double amount;
    @Getter @Setter
    private int operation;

    @Override
    public String toString() {
        return "Movements{" +
                "id=" + id +
                ", id_acc=" + id_acc +
                ", date=" + date  +
                ", amount=" + amount +
                ", operation=" + operation +
                '}';
    }

    public void toDataBase(Connection connection) throws SQLException {

        id_acc.toDataBase(connection);
        String query = "INSERT INTO movements(id, id_acc, date, amount, operation) VALUES(?, ?, ?, ?, ?)";

        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, this.getId());
        pstmt.setInt(2, this.getId_acc().getId());
        pstmt.setString(3, this.getDate());
        pstmt.setDouble(4, this.getAmount());
        pstmt.setInt(5, this.getOperation());

        pstmt.executeUpdate();
    }
}

