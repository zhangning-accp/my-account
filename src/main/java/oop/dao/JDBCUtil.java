package oop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by zn on 2018/7/18.
 * jdbc 工具类，提供一些常见的操作
 */
public class JDBCUtil {
    public static Connection getConnection() {
        //1. 加载驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //2. 创建数据库连接字符串
            String dbURL = "jdbc:mysql://127.0.0.1:3306/hnb11";
            //3. 建立数据库连接
            try {
                Connection connection = DriverManager.getConnection(dbURL,
                        "root","root");
                return connection;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void close(Connection connection,
                       Statement statement,
                       ResultSet resultSet) {
        try {
            if(resultSet != null) {
                resultSet.close();
            }
            if(statement != null) {
                statement.close();
            }
            if(connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

