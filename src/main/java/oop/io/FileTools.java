package oop.io;

import java.io.File;
import java.util.logging.Logger;

/**
 * Created by zn on 2018/7/20.
 */
public class FileTools {
   static Logger logger = Logger.getLogger(FileTools.class.getName());
    /**
     * copy文件
     * @param src 源文件
     * @param desc 目标文件
     *             就是将源文件内容复制到目标文件
     */
    public static void copy(String src,String desc) {
        File srcFile = new File(src);
        File descFile = new File(desc);
        if(srcFile.isDirectory()) {

        }

    }

    /**
     * 剪切操作
     * @param src 源文件
     * @param desc 目标文件
     *             将源文件内容复制到目标文件，同时删除源文件
     */
    public static void cut(String src,String desc){

    }

    /**
     * 删除文件
     * @param src 需要删除的文件
     */
    public static void delte(String src) {

    }

    /**
     *
     * @param folder
     */
    public static void delteFolder(String folder) {

    }
    /**
     * 拷贝文件夹
     * @param srcFolder 源文件夹
     * @param desFolder 目标文件夹
     *                  将原文件夹所有数据复制到目标文件夹。
     */
    public static void copyFolder(String srcFolder,String desFolder) {

    }
    /**
     * 剪切文件夹
     * @param srcFolder 源文件夹
     * @param desFolder 目标文件夹
     *                  将原文件夹所有数据复制到目标文件夹。同时删除源文件夹
     */
    public static void cutFolder(String srcFolder,String desFolder) {

    }
}
