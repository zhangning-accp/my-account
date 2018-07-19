package oop.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zn on 2018/7/18.
 * 对表Account的数据操作
 */
public class AccountDAO {


    public boolean delete(int id) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = JDBCUtil.getConnection();
            //2. 构建删除的sql语句
            String sql = "delete from account where id=" + id;
            //3. 执行删除语句
            statement = connection.createStatement();
            //4. 获取执行所影响的行数，判断是否执行成功
            int rows = statement.executeUpdate(sql);
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(connection,statement,null);
        }
        return false;
    }

    public boolean insert(Account account) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = JDBCUtil.getConnection();
            //2. 构建添加数据的sql语句
            String sql = "insert into account " +
                    "values(" + account.getId() + ",'"
                    + account.getUserAccount() + "','"
                    + account.getUserPassword() + "')";
            //System.out.println(sql);
            //3. 执行sql语句
            statement = connection.createStatement();
            //4. 得到执行后的结果，确定是否添加成功
            int rows = statement.executeUpdate(sql);
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(connection,statement,null);
        }
        return false;
    }

    public boolean update(Account account) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = JDBCUtil.getConnection();
            //2. 创建update sql 语句
            String sql = "update account set user_account='" + account.getUserAccount()
                    + "',user_password='" + account.getUserPassword()
                    + "' where id=" + account.getId();

            //3. 执行update 语句
            statement = connection.createStatement();
            //4. 得到执行结果，确定是否成功
            int rows = statement.executeUpdate(sql);
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(connection,statement,null);
        }
        return false;
    }
    public List<Account> findAll(){
        // 申明一个集合
        List<Account> list = new ArrayList<>();
        //1. 获取数据库连接
        Connection connection = JDBCUtil.getConnection();
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
            while(resultSet.next()) {// 定位到某一行
                Account account = new Account();
                int id = resultSet.getInt("id");
                String userAccount = resultSet.getString("user_account");
                String password = resultSet.getString("user_password");

                account.setId(id);
                account.setUserAccount(userAccount);
                account.setUserPassword(password);

                list.add(account);// 把每行数据放到集合里
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(connection,statement,resultSet);
        }
        return list;
    }
    public Account findById(int id){
        //1. 获取数据库连接
        Connection connection = JDBCUtil.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        Account account = null;
        //2. 构建查询的sql语句
        String sql = "select user_account,user_password,id from account where id=" + id;
        try {
            //3. 执行sql语句，并获得结果集
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            //4. 遍历结果集，输出每条记录的信息。
            if(resultSet.next()) {
                String userAccount = resultSet.getString("user_account");
                String password = resultSet.getString("user_password");
                // 构建找到的数据对象
                account = new Account();
                account.setId(id);
                account.setUserAccount(userAccount);
                account.setUserPassword(password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(connection,statement,resultSet);
        }
        return account;
    }
    public List<Account> findByKeyword(Object keyword) {
        return null;
    }

    private void test(int ...sumArray) {
        int sum = 0;
        for(int i = 0; i < sumArray.length; i ++) {
            sum += sumArray[i];
        }
        System.out.println("sum = " + sum);
    }
    public static void main(String ... args) {
        AccountDAO dao = new AccountDAO();
        //int [] sum = {1,2,3,4,5,7};
        dao.test(1,2,3,4,5,6,7);
        //List<Account> list = dao.findAll();
//        Account account = dao.findById(50);
//        System.out.println(account);
    }
}