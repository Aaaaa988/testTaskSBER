package com.kiselev.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.*;

@NoArgsConstructor
@AllArgsConstructor
public class Contracts {
    @Getter @Setter
    private int id;
    @Getter @Setter
    private String num;
    @Getter @Setter
    private Accounts acc_ref;
    @Getter @Setter
    private Kind_contracts kind_ref;

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("id: " + getId() + " num: " + getNum());
        if (acc_ref != null)
            stringBuilder.append(" acc_ref: " + getAcc_ref().getId());
            else
            stringBuilder.append(" acc_ref: (Не указан)");

        if (kind_ref != null)
            stringBuilder.append(" kind_ref: " + getKind_ref());
        else
            stringBuilder.append(" kind_ref: (Не указан)");
        return stringBuilder.toString();
    }

    public void toDataBase(Connection connection) throws SQLException {
        String query = "INSERT IGNORE INTO contracts(id, num,acc_ref, kind_ref) VALUES(?, ?, ?, ?)";

        PreparedStatement pstmt = connection.prepareStatement(query);

        pstmt.setInt(1, this.getId());
        pstmt.setString(2, this.getNum());
        if(acc_ref != null){
            acc_ref.toDataBase(connection);
            pstmt.setInt(3, acc_ref.getId());
        }else{
            pstmt.setNull(3, Types.INTEGER);
        }


        if(kind_ref != null){
            kind_ref.toDataBase(connection);
            pstmt.setInt(4, kind_ref.getId());
        }else{
            pstmt.setNull(4, Types.INTEGER);
        }

        pstmt.executeUpdate();

    }
}
