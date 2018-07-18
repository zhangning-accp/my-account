package oop.dao;


/**
 * Created by zn on 2018/7/18.
 * 实体类，代表表Account
 * 一个实体类对象等同于表里的一条数据记录
 */
public class Account {
    private int id;
    private String userAccount;
    private String userPassword;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
