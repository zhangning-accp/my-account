package oop.jdbc;

import com.sun.javafx.binding.StringFormatter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Created by zn on 2018/7/14.
 * 演示通过jdbc 连接数据库和进行增、删、改、查的操作
 */
public class JDBCDemo {

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

    private void insertData(int id,String accountValue,String password) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = getConnection();
            //2. 构建添加数据的sql语句
            String sql = "insert into account " +
                    "values(" + id + ",'" + accountValue + "','" + password + "')";
           //System.out.println(sql);
            //3. 执行sql语句
            statement = connection.createStatement();
            //4. 得到执行后的结果，确定是否添加成功
            int rows = statement.executeUpdate(sql);
            System.out.println("所影响的行数为：" + rows);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection,statement,null);
        }
    }

    private void deleteData(int id) {
        Connection connection = null;
        Statement statement = null;
        try {

            connection = getConnection();
            //2. 构建删除的sql语句
            String sql = "delete from account where id=" + id;
            //3. 执行删除语句
            statement = connection.createStatement();
            //4. 获取执行所影响的行数，判断是否执行成功
            int rows = statement.executeUpdate(sql);
            System.out.println("有" + rows + "行被删除成功！");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection,statement,null);
        }
    }

    private void updateData(int id,String account,String password) {
        Connection connection = null;
        Statement statement = null;

        try {

            connection = getConnection();
            //2. 创建update sql 语句
            String sql = "update account set user_account='" + account +
                    "',user_password='" + password + "' where id=" + id;
            //3. 执行update 语句
            statement = connection.createStatement();
            //4. 得到执行结果，确定是否成功
            int rows = statement.executeUpdate(sql);
            System.out.println("更新结果为：" + (rows > 0));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection,statement,null);
        }
    }

    public String [][] bestFindAllData() {
        // 申明一个100 x 3的数组，代表100行3列
        String [][] datas = new String [100][3];
        //1. 获取数据库连接
        Connection connection = getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        //2. 构建查询的sql语句
        String sql = "select user_account,user_password,id from account";
        try {
            //3. 执行sql语句，并获得结果集
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            //4. 遍历结果集，输出每条记录的信息。
            int index = 0;
             while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String account = resultSet.getString("user_account");
                String password = resultSet.getString("user_password");
                datas[index][0] = id + "";
                datas[index][1] = account;
                datas[index][2] = password;
                index ++;
             }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection,statement,resultSet);
        }
        return datas;
    }
    private void findAllDataFormatOutput() {
        String [][] datas = bestFindAllData();
            //4. 遍历结果集，输出每条记录的信息。
        StringBuffer buffer = new StringBuffer();
        buffer.append("------------------------------------------------------------------------------------------------" + System.lineSeparator());
        buffer.append("id\t\t\taccount\t\t\tpassword\t\t\t" + System.lineSeparator());
        buffer.append("------------------------------------------------------------------------------------------------" + System.lineSeparator());
        for(int i = 0; i < datas.length; i ++) {
          String [] values = datas[i];
          // 因为返回的数组里可能包含多余的数据，所以，需要进行过滤
          if(values[0] != null && values[1] != null && values[2] != null) {
              buffer.append(
                      String.format(
                              "%s\t|%s\t|%s", values[0], values[1], values[2]));
              buffer.append(System.lineSeparator());
          }
       }
       System.out.println(buffer.toString());
    }

    private void findAccountDataById(int id) {
        //1. 获取数据库连接
        Connection connection = getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        //2. 构建查询的sql语句
        String sql = "select user_account,user_password,id from account where id=" + id;
        try {
            //3. 执行sql语句，并获得结果集
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            //4. 遍历结果集，输出每条记录的信息。
            StringBuffer buffer = new StringBuffer();
            buffer.append("------------------------------------------------------------------------------------------------" + System.lineSeparator());
            buffer.append("id\t\t\taccount\t\t\tpassword\t\t\t" + System.lineSeparator());
            buffer.append("------------------------------------------------------------------------------------------------" + System.lineSeparator());
            while(resultSet.next()) {
                String account = resultSet.getString("user_account");
                String password = resultSet.getString("user_password");
                buffer.append(id + "\t| " + account + "| \t" + password + "|" + System.lineSeparator());
            }

            System.out.println(buffer.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection,statement,resultSet);
        }
    }

    /**
     * 模糊搜索数据，根据用户用户输入的关键词来模糊查询。
     * @param keyWord
     */
    public String [][] findAccountDataLikeKeyWord(String keyWord) {
        String [][] datas = new String [100][3];
        //1. 获取数据库连接
        Connection connection = getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        //2. 构建查询的sql语句
        String sql = "select user_account,user_password,id from account " +
                "where user_account like '%" + keyWord + "%' or user_password like '%" + keyWord + "%'";
        try {
            //3. 执行sql语句，并获得结果集
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            int index = 0;
            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String account = resultSet.getString("user_account");
                String password = resultSet.getString("user_password");
                datas[index][0] = id + "";
                datas[index][1] = account;
                datas[index][2] = password;
                index ++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection,statement,resultSet);
        }
        return datas;
    }

    private void close(Connection connection,
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
    public static void main(String [] args) {
        Scanner scanner = new Scanner(System.in);
        JDBCDemo jdbcDemo = new JDBCDemo();
        //jdbcDemo.findAccountDataLikeKeyWord("5");
        while (true) {
            System.out.println("=============================================================");
            System.out.println("|     欢迎使用HNB 11 人工智能系统  请选择你要进行的操作:   |");
            System.out.println("| 1.添加数据   2.修改数据   3.删除数据   4. 查看数据 5.退出系统        |");
            System.out.println("=============================================================");
            int select = 0;//接收用户选择的选项。
            select = scanner.nextInt();
            String value = null;
            if (select == 1) {//添加数据
                System.out.println("请输入要添加的账号和密码，中间用逗号分隔.举例：126.com,3456");
                value = scanner.next();
                String[] values = value.split(",");
                jdbcDemo.insertData((int) System.currentTimeMillis(),
                        values[0], values[1]);
            } else if (select == 2) {// 修改数据
                System.out.println("请输入要修改的id、账号和密码。逗号分隔。系统将根据id进行数据的更新。id本身不会更新请放心..");
                value = scanner.next();
                String[] values = value.split(",");
                jdbcDemo.updateData(Integer.parseInt(values[0]),
                        values[1],values[2]);
            } else if (select == 3) {// 删除数据
                System.out.println("请输入要删除的id");
                value = scanner.next();
                jdbcDemo.deleteData(Integer.parseInt(value));
            } else if (select == 4) {// 退出系统
                jdbcDemo.findAllDataFormatOutput();
            } else if (select == 5) {// 退出系统
                System.exit(-1);
            } else {
                System.out.println("选择的操作不能识别，请重新选择：");
            }
        }
    }
}
