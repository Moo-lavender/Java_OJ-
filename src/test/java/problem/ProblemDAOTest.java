package problem;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ProblemDAOTest {

    @Test
    public void selectAll() {
        ProblemDAO problemDAO = new ProblemDAO();
        List<Problem> problems = problemDAO.selectAll();
        System.out.println("selectAll: "+ problems);
    }

    @Test
    public void selectOne() {
        ProblemDAO problemDAO = new ProblemDAO();
        Problem problem = problemDAO.selectOne(1);
        System.out.println("selectOne: "+ problem);
    }

    @Test
    public void insert() {
        Problem problem = new Problem();
        problem.setTitle("各位相加");
        problem.setLevel("简单");
        problem.setDescription("给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。\n" +
                "\n" +
                "示例:\n" +
                "\n" +
                "输入: 38\n" +
                "输出: 2 \n" +
                "解释: 各位相加的过程为：3 + 8 = 11, 1 + 1 = 2。 由于 2 是一位数，所以返回 2。\n" );
        problem.setTemplateCode("public class Solution {\n" +
                "    public int addDigits(int num) {\n" +
                "\n" +
                "    }\n" +
                "}");
        problem.setTestCode("public static void main(String[] args) {\n" +
                "   Solution s = new Solution();\n" +
                "   if (s.addDigits(38) == 2) {\n" +
                "       System.out.println(\"test OK!\");\n" +
                "   }else {\n" +
                "       System.out.println(\"test failed!\");\n" +
                "   }\n" +
                "   if (s.addDigits(1) == 1) {\n" +
                "       System.out.println(\"test OK!\");\n" +
                "   }else {\n" +
                "       System.out.println(\"test failed!\");\n" +
                "   }\n" +
                "}");
        ProblemDAO problemDAO = new ProblemDAO();
        problemDAO.insert(problem);
        System.out.println("insert ok");
    }

    @Test
    public void delete() {
    }
}