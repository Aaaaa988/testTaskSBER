package com.kiselev.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
