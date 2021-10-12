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
public class Types_reg {
    @Getter @Setter
    private int id;
    @Getter @Setter
    private String name;

    @Override
    public String toString(){
        return "id: " + getId() + " name: " + getName();
    }

    public void toDataBase(Connection connection) throws SQLException {
        String query = "INSERT IGNORE INTO types_reg(id, name) VALUES(?, ?)";

        PreparedStatement pstmt = connection.prepareStatement(query);

        pstmt.setInt(1, this.getId());
        pstmt.setString(2, this.getName());

        pstmt.executeUpdate();
    }
}
