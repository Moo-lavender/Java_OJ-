package problem;

import common.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//数据访问层
public class ProblemDAO {
    //获取所有题目信息
    public List<Problem> selectAll() {
        List<Problem> result = new ArrayList<>();
        //1.获取数据库连接
        Connection connection = DBUtil.getConnection();
        String sql = "select * from oj_table";
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Problem problem = new Problem();
                problem.setId(resultSet.getInt("id"));
                problem.setTitle(resultSet.getString("title"));
                problem.setLevel(resultSet.getString("level"));
//                problem.setDescription(resultSet.getString("description"));
//                problem.setTemplateCode(resultSet.getString("templateCode"));
//                problem.setTestCode(resultSet.getString("testCode"));
                result.add(problem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,statement,resultSet);
        }
        return result;
    }

    //获取指定 id 的题目信息
    public Problem selectOne(int id) {
        Connection connection = DBUtil.getConnection();
        String sql = "select * from oj_table where id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Problem problem = new Problem();
                problem.setId(resultSet.getInt("id"));
                problem.setTitle(resultSet.getString("title"));
                problem.setLevel(resultSet.getString("level"));
                problem.setDescription(resultSet.getString("description"));
                problem.setTemplateCode(resultSet.getString("templateCode"));
                problem.setTestCode(resultSet.getString("testCode"));
                return problem;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,statement,resultSet);
        }
        return null;
    }
    //新增一个题目
    public void insert (Problem problem) {
        //1.获取数据库连接
        Connection connection = DBUtil.getConnection();
        //2.拼装sql语句
        String sql = "insert into oj_table values(null,?,?,?,?,?)";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1,problem.getTitle());
            statement.setString(2,problem.getLevel());
            statement.setString(3,problem.getDescription());
            statement.setString(4,problem.getTemplateCode());
            statement.setString(5,problem.getTestCode());
            System.out.println("insert:" + statement);
            //3.执行sql语句
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //4.关闭释放相关资源
            DBUtil.close(connection,statement,null);
        }
    }

    //删除指定的题目信息
    public void delete(int id) {
        Connection connection = DBUtil.getConnection();
        String sql = "delete from oj_table where id = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,statement,null);
        }
    }

    public static void main(String[] args) {}
}
