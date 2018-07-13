import java.util.Scanner;

/**
 * Created by zn on 2018/7/13.
 */
public class CheckTest {
    public static void main(String ... args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("输入任意字符开始.....");
            String start = scanner.next();
            int random = ((int)(Math.random() * 43)) + 1;
            System.out.print("正在随机抽取中奖号码\t");
            for(int i = 0; i < 20; i ++) {
                System.out.print("■");
                Thread.sleep(300);
            }
            System.out.println("\n中奖号:" + random);
        }
    }
}
