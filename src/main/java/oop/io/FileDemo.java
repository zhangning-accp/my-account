package oop.io;

import java.io.File;
import java.io.IOException;

/**
 * Created by zn on 2018/7/20.
 * File类使用的演示
 */
public class FileDemo {


    public static void main(String [] args) {
        File file = new File("a/b/c");
        String path = file.getAbsolutePath();
        System.out.println(path);
        // 创建目录
        file.mkdirs();
        if(file.isDirectory()) {
            System.out.println("目录");
        }
        if(file.isFile()) {
            System.out.println("文件");
        }
        if(file.exists()) {
            System.out.println("file exists: true" );
        }else {
            System.out.println("file exists: false" );
        }
    }
}
