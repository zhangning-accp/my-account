package oop.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

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
     * 该方法的作用是获取一个数据库连接对象
     */
    private Connection getConnection() {
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

    private void testInsertData(int id,String accountValue,String password) {
        try {
            Connection connection = getConnection();
            //2. 构建添加数据的sql语句
            String sql = "insert into account " +
                    "values(" + id + ",'" + accountValue + "','" + password + "')";
           //System.out.println(sql);
            //3. 执行sql语句
            Statement statement = connection.createStatement();
            //4. 得到执行后的结果，确定是否添加成功
            int rows = statement.executeUpdate(sql);
            System.out.println("所影响的行数为：" + rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void testDeleteData(int id) {
        try {

            Connection connection = getConnection();
            //2. 构建删除的sql语句
            String sql = "delete from account where id=" + id;
            //3. 执行删除语句
            Statement statement = connection.createStatement();
            //4. 获取执行所影响的行数，判断是否执行成功
            int rows = statement.executeUpdate(sql);
            System.out.println("有" + rows + "行被删除成功！");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void testUpdateData(int id,String account,String password) {
        try {

            Connection connection = getConnection();
            //2. 创建update sql 语句
            String sql = "update account set user_account='" + account +
                    "',user_password='" + password + "' where id=" + id;
            //3. 执行update 语句
            Statement statement = connection.createStatement();
            //4. 得到执行结果，确定是否成功
            int rows = statement.executeUpdate(sql);
            System.out.println("更新结果为：" + (rows > 0));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String [] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("=============================================================");
            System.out.println("|     欢迎使用HNB 11 人工智能系统  请选择你要进行的操作:   |");
            System.out.println("| 1.添加数据   2.修改数据   3.删除数据   4.退出系统      |");
            System.out.println("=============================================================");
            int select = 0;//接收用户选择的选项。
            select = scanner.nextInt();
            while (select < 1 || select > 4) {
                System.out.println("选择的操作不能识别，请重新选择：");
                select = scanner.nextInt();
            }
            String value = null;
            JDBCDemo jdbcDemo = new JDBCDemo();
            if (select == 1) {//添加数据
                System.out.println("请输入要添加的账号和密码，中间用逗号分隔.举例：126.com,3456");
                value = scanner.next();
                String[] values = value.split(",");
                jdbcDemo.testInsertData((int) System.currentTimeMillis(),
                        values[0], values[1]);
            } else if (select == 2) {// 修改数据
                System.out.println("请输入要修改的id、账号和密码。逗号分隔。系统将根据id进行数据的更新。id本身不会更新请放心..");
                value = scanner.next();
                String[] values = value.split(",");
                jdbcDemo.testUpdateData(Integer.parseInt(values[0]),
                        values[1],values[2]);
            } else if (select == 3) {// 删除数据
                System.out.println("请输入要删除的id");
                value = scanner.next();
                jdbcDemo.testDeleteData(Integer.parseInt(value));
            } else if (select == 4) {// 退出系统
                System.exit(-1);
            }
        }
    }
}
