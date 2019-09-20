package main.java;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Generatefiles {
    /**
     *在指定文件夹新建shell脚本文件
     *
     * @param fileName 文件名称
     * @param path 生成路径
     * @param contents 生成内容，一个数据换行一次
     */
    public Boolean Generate(String fileName, String path, ArrayList<String> contents){
        if(path.equals("")){
            return false;
        }
        //判断文件是否存在，不存在就新建
        File file = new File(path+fileName);
        if (!file.exists()) {
            try {
                //新建文件
                file.createNewFile();
                //往File写数据
                FileWriter fileWriter = new FileWriter(path);
                //因为不同系统换行符号不一致，所以使用BufferedWriter内部定义的换行.newLine()方法，这样就不用在意换行
                BufferedWriter bw = new BufferedWriter(fileWriter);
                for(String content :contents){
                    bw.write(content);//添加数据
                    bw.newLine();//换行
                }
                bw.flush();
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
