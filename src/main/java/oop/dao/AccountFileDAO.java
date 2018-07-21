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
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.omg.PortableInterceptor.ACTIVE;

/**
 * Created by zn on 2018/7/21.
 * 负责account 实体 的文件数据操作的DAO
 * Data access object
 */
public class AccountFileDAO implements IAccountDAO {
    private static final String FILE_NAME = "account.hnb";
    public boolean delete(int id) {
        List<Account> list = findAll();
//        Iterator<Account> iterator = list.iterator();
//        while(iterator.hasNext()) {
//            if(iterator.next().getId() == id) {
//                iterator.remove();
//            }
//        }
//
//        for(int i = 0; i < list.size(); i ++) {
//            if(list.get(i).getId() == id) {
//                list.remove(list.get(i));
//                break;
//            }
//        }
        boolean isRemove = list.removeIf(p->p.getId() == id);
        StringBuffer buffer = new StringBuffer();
        for(Account account : list) {
            buffer.append(builderAccount2String(account));
        }
        writer2File(buffer.toString());
        return  isRemove;
    }

    public boolean insert(Account account) {
        if(account == null) {
            return false;
        }
        StringBuffer buffer = new StringBuffer();
        String str = rederFile(FILE_NAME);
        buffer.append(str);

        //1. 选择一个字符文件输出流
        try(FileWriter writer = new FileWriter(FILE_NAME)) {
            //2. 将数据拼接成我们所需要的字符串格式： id account password
            buffer.append(builderAccount2String(account));
            writer.write(buffer.toString());//3. 保存到文件
            return true;
        } catch (IOException e) {e.printStackTrace();}

        return false;
    }

    public boolean update(Account account) {
        List<Account> list = findAll();
//        for(Account a : list) {
//            if(a.getId() == account.getId()) {
//                a.setId();
//            }
//        }
        list.stream().forEach(p->{
            //找到需要修改的数据(通过id来确定)，将对应对象的账号和密码修改为传入的对象数据
            if(p.getId() == account.getId()) {
                p.setUserAccount(account.getUserAccount());
                p.setUserPassword(account.getUserPassword());
            }
        });
        // 保存到文件
        StringBuffer buffer = new StringBuffer();
        for(Account tmp : list) {
            buffer.append(builderAccount2String(tmp));
        }
        writer2File(buffer.toString());
        return false;
    }

    public List<Account> findAll(){
        List<Account> list = new ArrayList<>();
        //1. 创建输入流  //2. 读取数据
        String str = rederFile(FILE_NAME);
        String [] rows = str.split(System.lineSeparator());
        for(String row : rows) {
            String [] colums = row.split(" ");
            Account account = new Account();
            account.setId(Integer.parseInt(colums[0]));
            account.setUserAccount(colums[1]);
            account.setUserPassword(colums[2]);
//            for(int i = 0; i < colums.length; i ++) {
//                switch (i) {
//                    case 0: {
//                        account.setId(Integer.parseInt(colums[0]));
//                    }case 1:{
//                        account.setUserAccount(colums[1]);
//                    }case 2:{
//                        account.setUserPassword(colums[2]);
//                    }
//                }
//            }
            list.add(account);
    }
        //3. 拆分字符串
        return list;
    }
    public Account findById(int id){
        List<Account> list = findAll();
//        for(Account account : list) {
//            if(account.getId() == id) {
//                return account;
//            }
//        }
       Account account = list.stream().filter(p->p.getId() == id)
               .findFirst().get();

        return account;
    }

    public List<Account> findByKeyword(Object keyword) {
        List<Account> list = findAll();
//        List<Account> filters = new ArrayList<>();
//        for(Account account : list) {
//            if(account.getUserPassword().contains(keyword + "")
//                    || account.getUserAccount().contains(keyword + "")) {
//                filters.add(account);
//            }
//        }
        list = list.stream().filter(p->{
            if(p.getUserPassword().contains(keyword + "")
                    || p.getUserAccount().contains(keyword + "")) {
               return true;
            }
            return false;
        }).collect(Collectors.toList());

        return list;
    }

    /**
     * 读取指定的文件，并将内容已String 返回
     * @param fileName
     * @return
     */
    private String rederFile(String fileName) {
        //读取出文件里所有的数据，然后将新的数据追加到旧的数据上，并整体保存
        StringBuffer buffer = new StringBuffer();
        try(BufferedReader reader = new BufferedReader(
                new FileReader("account.hnb"))) {
            // 读取一行数据，io判定一行的标准是看有没有换行符。如果有就是一行
            // 如果没有则不是。
            String str = reader.readLine();
            // 为空的表示没有读取到任何数据，所以多个行之间如果有空行，那么空行后的数据
            // 将不会被读取。
            while(str != null && !str.trim().equals("")) {
                // 拿到当前行数据，并添加一个换行，否则所有的数据都在一行上，不利于后期处理
                buffer.append(str + System.lineSeparator());
                str = reader.readLine();// 读取下一行数据
            }
        } catch (FileNotFoundException e) {e.printStackTrace();
        } catch (IOException e) {e.printStackTrace();}

        return buffer.toString();
    }

    private void writer2File(String content) {
        //1. 选择一个字符文件输出流
        try(FileWriter writer = new FileWriter(FILE_NAME)) {
            //2. 将数据拼接成我们所需要的字符串格式： id account password
            writer.write(content);//3. 保存到文件
        } catch (IOException e) {e.printStackTrace();}
    }

    private String builderAccount2String(Account account) {
        String str = account.getId() + " " +
                account.getUserAccount() + " " +
                account.getUserPassword() +
                System.lineSeparator();
        return str;
    }

    public static void main(String [] args) {
        AccountFileDAO dao = new AccountFileDAO();
//        Account account = new Account();
//        account.setId(12);
//        account.setUserAccount("1211.com");
//        account.setUserPassword("sdf11");
//        dao.insert(account);
        List<Account> list = dao.findByKeyword("5");
        System.out.println(list);
    }
}
