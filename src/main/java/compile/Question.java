package compile;

//要编译的代码
public class Question {
    //要编译执行的代码内容
    private String code;

    //执行时标准输入的内容
    private String stdin;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStdin() {
        return stdin;
    }

    public void setStdin(String stdin) {
        this.stdin = stdin;
    }
}
