package com.kiselev.database;

import lombok.Getter;
import lombok.Setter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesLoad {
    private static PropertiesLoad instance;
    @Getter @Setter
    private String host;
    @Getter @Setter
    private String login;
    @Getter @Setter
    private String password;
    @Getter @Setter
    private String baseName;

    public PropertiesLoad() throws IOException {
        FileInputStream fis;
        Properties property = new Properties();
        fis = new FileInputStream("src/main/resources/db.properties");
        property.load(fis);
        this.host = property.getProperty("db.host");
        this.login = property.getProperty("db.login");
        this.password = property.getProperty("db.password");
        this.baseName = property.getProperty("db.baseName");
        System.out.println("/*-----------Properties:----------------------------------------------------------------*/");
        System.out.println("HOST: " + host + ", BaseName: " + baseName + ", LOGIN: " + login + ", PASSWORD: " + password );
        System.out.println("/*--------------------------------------------------------------------------------------*/");
    }

    public static PropertiesLoad getInstance() throws IOException {
        if (instance == null) {
            instance = new PropertiesLoad();
        }
        return instance;
    }
}
