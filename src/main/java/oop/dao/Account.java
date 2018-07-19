package oop.dao;


import lombok.Data;

/**
 * Created by zn on 2018/7/18.
 * 实体类，代表表Account
 * 一个实体类对象等同于表里的一条数据记录
 */
@Data
public class Account {
    private int id;
    private String userAccount;
    private String userPassword;
}
