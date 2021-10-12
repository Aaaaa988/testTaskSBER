package com.kiselev.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
