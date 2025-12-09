package com.crosshair;

import java.io.*;
import java.util.Properties;

public class ConfigManager {

    private static final String CONFIG_FILE = "config.properties";
    private static final Properties props = new Properties();

    static {
        loadConfig();
    }

    public static void loadConfig() {
        try {
            File file = new File(CONFIG_FILE);
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                props.load(fis);
                fis.close();
                System.out.println("[Config] Loaded config.properties");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveConfig() {
        try {
            FileOutputStream fos = new FileOutputStream(CONFIG_FILE);
            props.store(fos, "Crosshair Config");
            fos.close();
            System.out.println("[Config] Saved config.properties");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void set(String key, String value) {
        props.setProperty(key, value);
        saveConfig();
    }

    public static String get(String key, String defaultValue) {
        return props.getProperty(key, defaultValue);
    }
}
