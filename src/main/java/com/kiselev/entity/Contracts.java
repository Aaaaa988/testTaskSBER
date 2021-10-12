package com.kiselev.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
