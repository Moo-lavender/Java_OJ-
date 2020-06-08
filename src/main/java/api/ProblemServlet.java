package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import problem.Problem;
import problem.ProblemDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProblemServlet extends HttpServlet {
    private Gson gson = new GsonBuilder().create();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id == null || id.equals("")){
            //没有id这个参数，执行查找全部
            selectAll(resp);
        } else {
            //存在id，执行查找指定题目
            selectOne(Integer.parseInt(id),resp);
        }
    }

    private void selectAll(HttpServletResponse resp) throws IOException {
        //ConteneTypee 描述了body中的数据类型
        //常见取值：
        //html:text/html
        //图片：image/png image/jpg
        //json：application/json
        //css：text/css
        //JavaScript：application/JavaScript

        resp.setContentType("application/json;charset=utf-8");
        ProblemDAO problemDAO = new ProblemDAO();
        List<Problem> problems = problemDAO.selectAll();
        //把结果组织成json结构
        String jsonString = gson.toJson(problems);//把对象变成json
        resp.getWriter().write(jsonString);
    }

    private void selectOne(int problemId, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json;charset=utf-8");
        ProblemDAO problemDAO = new ProblemDAO();
        Problem problem = problemDAO.selectOne(problemId);
        //测试代码不应该告诉前端，此时手动把这个内容清理
        problem.setTestCode("");
        String jsonString = gson.toJson(problem);
        resp.getWriter().write(jsonString);
    }
}
