package com.kiselev.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

@NoArgsConstructor
@AllArgsConstructor
public class Accounts {
    @Getter @Setter
    private int id;
    @Getter @Setter
    private String num;

    @Override
    public String toString(){
        return "id: " + getId() + " num: " + getNum();
    }

    public void toDataBase(Connection connection) throws SQLException {
        String query = "INSERT IGNORE INTO accounts(id, num) VALUES(?, ?)";

        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, this.getId());
        pstmt.setString(2, this.getNum());

        pstmt.executeUpdate();
    }
}
