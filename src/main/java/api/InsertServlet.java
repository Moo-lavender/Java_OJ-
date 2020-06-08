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
import java.io.InputStream;

public class InsertServlet extends HttpServlet {
    private Gson gson = new GsonBuilder().create();

    static class InsertReq{
        //题目名称
        private String title;

        //题目难度
        private String level;

        //题目描述
        private String description;

        //题目给定代码模块
        private String templateCode;

        //题目测试用例
        private String testCode;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getTemplateCode() {
            return templateCode;
        }

        public void setTemplateCode(String templateCode) {
            this.templateCode = templateCode;
        }

        public String getTestCode() {
            return testCode;
        }

        public void setTestCode(String testCode) {
            this.testCode = testCode;
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Problem problem = new Problem();
        //1. 读取body数据
        String body =  readBody(req);
        //2. 按照API约定的格式来解析JSON数据，得到InsertReq
        InsertReq insertReq = gson.fromJson(body,InsertReq.class);
        //3. 解析出每行数据进行校验
        if (insertReq.getTitle() == null || insertReq.getLevel() == null || insertReq.getDescription() == null ||
            insertReq.getTemplateCode() == null || insertReq.getTestCode() == null) {
            resp.getWriter().write("插入题目不规范，插入失败");
            return;
        }
        //4. 包装成problem对象,在数据库中执行
        problem.setTitle(insertReq.getTitle());
        problem.setLevel(insertReq.getLevel());
        problem.setDescription(insertReq.getDescription());
        problem.setTemplateCode(insertReq.getTemplateCode());
        problem.setTestCode(insertReq.getTestCode());
        //5. 返回插入
        ProblemDAO problemDAO = new ProblemDAO();
        problemDAO.insert(problem);
        resp.getWriter().write("插入成功");
    }

    private String readBody(HttpServletRequest req) {
        int contentlength = req.getContentLength();
        byte[] buf = new byte[contentlength];

        try(InputStream ins = req.getInputStream()) {
            ins.read(buf,0,contentlength);
        }catch (IOException e){
            e.printStackTrace();
        }
        return new String(buf);
    }


}
