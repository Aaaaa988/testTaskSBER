package com.kiselev.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
