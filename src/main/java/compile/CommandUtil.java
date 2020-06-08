package compile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

//让Java执行一个具体的指令如 ：javac
public class CommandUtil {
    //cmd表示要执行的命令
    //stdoutFile 表示输出结果重定向到那个文件中,如果为null表示不需要重定向
    //stderrFile 表示标准错误结果重定向到哪个文件中
    public static int run(String cmd,String stdoutFile,String stderrFile) throws IOException, InterruptedException {

        //1. 获取Runtime对象，它是一个单例对象
        Runtime runtime = Runtime.getRuntime();
        //2. 通过exec方法返回一个进程进行操作
        //相当于直接在命令行中执行cmd
        Process process = runtime.exec(cmd);

        //3. 针对标准输出重定向
        if (stdoutFile != null) {
            //获取到进程标准输出结果
            InputStream stdoutFrom = process.getInputStream();
            OutputStream stdoutTo = new FileOutputStream(stdoutFile);
            int ch = -1;
            while ((ch = stdoutFrom.read()) != -1) {
                stdoutTo.write(ch);
            }
            stdoutFrom.close();
            stdoutTo.close();
        }

        //4. 针对标准错误进行重定向
        if (stderrFile != null) {
            InputStream stderrFrom = process.getErrorStream();
            OutputStream stderrTo = new FileOutputStream(stderrFile);
            int ch = -1;
            while ((ch = stderrFrom.read()) != -1) {
                stderrTo.write(ch);
            }
            stderrFrom.close();
            stderrTo.close();
        }

        //5. 为了确保子进程先执行完，需要加上等待
        //父进程会在waitFor阻塞等待，直到子进程执行结束在执行
        int exitCode = process.waitFor();
        return exitCode;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        run("javac","f:/stdoutFile","f:/stderrFile");
    }
}
