package oop.c1;

import lombok.Data;
import lombok.ToString;

/**
 * Created by zn on 2018/7/11.
 */
@Data
@ToString
public class User {
    private String name = null;
    private short age = 0;
    private static String address = null;
    static {
        address = "";

    }

    public static void setAddress(String add) {
        address = add;
    }

    @Override
    public String toString() {
        return "name:" + name + ",age:" + age + ",address:" + address;
    }
}
