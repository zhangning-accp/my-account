package oop.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zn on 2018/7/21.
 * 负责account 实体 的文件数据操作的DAO
 * Data access object
 */
public class AccountFileDAO {
    public boolean delete(int id) {
        return  false;
    }

    public boolean insert(Account account) {
        if(account == null) {
            return false;
        }
        //读取出文件里所有的数据，然后将新的数据追加到旧的数据上，并整体保存
        StringBuffer buffer = new StringBuffer();
        try(BufferedReader reader = new BufferedReader(
                new FileReader("account.hnb"))) {
                String str = reader.readLine();
                while(str != null && !str.trim().equals("")) {
                    buffer.append(str + System.lineSeparator());
                    str = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //1. 选择一个字符文件输出流
        try(FileWriter writer = new FileWriter("account.hnb");) {
            //2. 将数据拼接成我们所需要的字符串格式
            String data = account.getId() + " " +
                    account.getUserAccount() + " " +
                    account.getUserPassword();
            buffer.append(data + System.lineSeparator());
            writer.write(buffer.toString());//3. 保存到文件
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Account account) {
        return false;
    }

    public List<Account> findAll(){
        //1. 创建输入流
        //2. 读取数据
        //3. 拆分字符串
        return null;
    }
    public Account findById(int id){
        return null;
    }

    public List<Account> findByKeyword(Object keyword) {
        return null;
    }

    public static void main(String [] args) {
        AccountFileDAO dao = new AccountFileDAO();
        Account account = new Account();
        account.setId(12);
        account.setUserAccount("1211.com");
        account.setUserPassword("sdf11");
        dao.insert(account);
    }
}
