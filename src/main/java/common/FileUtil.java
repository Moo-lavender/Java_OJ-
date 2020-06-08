package common;

import java.io.*;

//简单的工具类，帮我们更方便的读写文件
public class FileUtil {
    //读文件：一下把整个文件内容都读取到String中
    public static String readFile(String filePath) {
        //当前涉及到的编译错误、标准输出结果等内容是文本文件，此处使用字符流

        try(FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            StringBuilder stringBuilder = new StringBuilder();
            //按行读取
            String line = null;
            while ((line = bufferedReader.readLine())!= null) {
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    //写文件：一下把整个String的内容都写到指定文件中
    public static void writeFile (String filePath,String content) {
        try(FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(content);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
