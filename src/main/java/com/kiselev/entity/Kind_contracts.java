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
public class Kind_contracts {
    @Getter @Setter
    private int id;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private Types_reg types_reg;

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("id: " + getId() + " name: " + getName());
        if (types_reg != null)
            stringBuilder.append(" types_reg: " + getTypes_reg().getId());
        else
            stringBuilder.append(" types_reg: (Не указан)");

        return stringBuilder.toString();
    }

    public void toDataBase(Connection connection) throws SQLException {
        String query = "INSERT IGNORE INTO kind_contracts(id, name , type_ref) VALUES(?, ?, ?)";

        PreparedStatement pstmt = connection.prepareStatement(query);

        pstmt.setInt(1, this.getId());
        pstmt.setString(2, this.getName());
        if(types_reg != null){
            types_reg.toDataBase(connection);
            pstmt.setInt(3, types_reg.getId());
        }else
            pstmt.setNull(3, Types.INTEGER);

        pstmt.executeUpdate();
    }
}
