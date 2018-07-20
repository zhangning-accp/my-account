package oop.io;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by zn on 2018/7/20.
 * 演示io操作
 */
public class IODemo {
    /**
     * 将字符串内容写入到文件
     */
    private void writeStringT2File() {
        FileWriter writer = null;
        try {//创建输出流对象，目标：是abc.txt.也就是要将数据写入到abc.txt文件
            writer = new FileWriter("abc.txt");
            // 数据来源：是内存中的字符串内容
            String content = "大家好！我是xxx\n你们想我吗！\n no!no!no!";
            writer.write(content);//将字符串内容写入到文件。
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();//关闭输出流，试试如果不关闭是什么情况。
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        IODemo ioDemo = new IODemo();
        ioDemo.writeStringT2File();
    }
}
