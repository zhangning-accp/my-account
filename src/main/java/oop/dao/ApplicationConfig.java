package oop.dao;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by zn on 2018/7/22.
 * 应用程序配置类，负责读取application.config文件。
 */
public class ApplicationConfig {
    private static Properties config = null;
    static {
        config = new Properties();
        try {
            config.load(new FileReader("application.config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getDriver() {
        return (String) config.get("driver");
    }
    public static String getDbUrl() {
        return (String) config.get("dbUrl");
    }
    public static String getUserName() {
        return (String) config.get("userName");
    }
    public static String getPassword() {
        return (String) config.get("userPassword");
    }
    public static String getClassName() {
        return (String) config.get("daoClassName");
    }
}
