package com.example.mailsender.util;

import lombok.Getter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class Config {
    private static Config instance;

    @Getter
    private final String password;

    private Config() throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("src/main/resources/password.properties");
        properties.load(fileInputStream);
        password = properties.getProperty("password");
    }

    public static Config getInstance() throws IOException {
        return instance == null ? instance = new Config() : instance;
    }
}
