package oop.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zn on 2018/7/21.
 */
public interface IAccountDAO {
    boolean delete(int id);
    boolean insert(Account account);
    boolean update(Account account);
    List<Account> findAll();
    Account findById(int id);
    List<Account> findByKeyword(Object keyword);
}
