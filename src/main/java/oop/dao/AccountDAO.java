package oop.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
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
        //
        return null;
    }
    public Account findById(int id){
        return null;
    }
    public List<Account> findByKeyword(Object keyword) {
        return null;
    }
}