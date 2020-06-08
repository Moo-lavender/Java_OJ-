package compile;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class TaskTest {

    @Test
    public void compileAndRun() throws IOException, InterruptedException {
        Question question = new Question();
        question.setCode(
                "public class Solution {\n" +
                        "   public static void main(String[] args) {\n" +
                        "       System.out.println(\"hello\");\n" +
                        "   }\n" +
                        "}\n"
        );
        question.setStdin("");
        Task task = new Task();
        Answer answer = task.compileAndRun(question);
        System.out.println(answer);
    }
}