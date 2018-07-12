import java.util.Scanner;
import oop.c1.User;;

/**
 * Created by zn on 2018/7/11.
 */
public class UserTest {
    public static void main(String [] args) {
        User user1 = new User();
        user1.setAge((short) 10);
        user1.setName("user1");
        user1.setAddress("四川成都");
        System.out.println("user1:" + user1);
        User user2 = new User();
        user2.setAge((short) 20);
        user2.setName("user2");
        user2.setAddress("四川达州");
        System.out.println("user2:" + user2);
        System.out.println("user1:" + user1);
    }


}
