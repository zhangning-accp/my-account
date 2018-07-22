package oop.pro;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by zn on 2018/7/22.
 */
public class PropertiesDemo {
    public static void main(String [] args) {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("application.config"));
            properties.list(System.out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        properties.put("driver","com.mysql.jdbc.Driver");
        properties.put("userName","root");

        try {
            properties.store(new FileWriter("application.config"),null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
