package oop.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by zn on 2018/7/14.
 * 演示通过jdbc 连接数据库和进行增、删、改、查的操作
 */
public class JDBCDemo {
    static {
        System.out.println("JDBCDemo static....");
    }
    public JDBCDemo() {
        System.out.print("JDBCDemo..");
    }
    /**
     * 演示通过jdbc连接数据库
     */

    private void testConnection() {
        //1. 加载驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //2. 创建数据库连接字符串
            String dbURL = "jdbc:mysql://127.0.0.1:3306/hnb11";
            //3. 建立数据库连接
            try {
                Connection connection = DriverManager.getConnection(dbURL,
                        "root","root");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void testInsertData(int id,String accountValue,String password) {

        try {
            //1. 创建数据库连接
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hnb11",
                    "root","root");
            //2. 构建添加数据的sql语句
            String sql = "insert into account " +
                    "values(" + id + ",'" + accountValue + "','" + password + "')";
           //System.out.println(sql);
            //3. 执行sql语句
            Statement statement = connection.createStatement();
            //4. 得到执行后的结果，确定是否添加成功
            int rows = statement.executeUpdate(sql);
            System.out.println("所影响的行数为：" + rows);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void testDeleteData(int id) {
        try {
            //1. 创建数据库连接
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/hnb11",
                    "root","root");
            //2. 构建删除的sql语句
            String sql = "delete from account where id=" + id;
            //3. 执行删除语句
            Statement statement = connection.createStatement();
            //4. 获取执行所影响的行数，判断是否执行成功
            int rows = statement.executeUpdate(sql);
            System.out.println("有" + rows + "行被删除成功！");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void testUpdateData(int id,String account,String password) {
        try {
            //1. 创建数据库连接
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hnb11?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true",
                    "root","root");
            //2. 创建update sql 语句
            String sql = "update account set user_account='" + account +
                    "',user_password='" + password + "' where id=" + id;
            //3. 执行update 语句
            Statement statement = connection.createStatement();
            //4. 得到执行结果，确定是否成功
            int rows = statement.executeUpdate(sql);
            System.out.println("更新结果为：" + (rows > 0));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String [] args) {
        JDBCDemo demo = new JDBCDemo();
        //demo.testInsertData(4,"yahoo.com","4567");
        demo.testInsertData(5,
                "gmail.com",
                "123456");
    }
}
