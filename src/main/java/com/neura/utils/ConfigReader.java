package com.neura.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    private static Properties props;

    static {
        try {
            FileInputStream input = new FileInputStream("src/test/resources/config.properties");
            props = new Properties();
            props.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Config file cannot be loaded", e);
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}
