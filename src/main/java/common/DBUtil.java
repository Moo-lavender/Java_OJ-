package common;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//借助这个类和数据库建立连接
public class DBUtil {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/java_oj?characterEncoding=utf8&useSSL=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "hj.1009";

    private static volatile DataSource dataSourse = null;

    private DBUtil(){}

    private static DataSource getDataSourse(){
        if (dataSourse == null) {
            synchronized(DBUtil.class) {
                if (dataSourse == null) {
                    dataSourse = new MysqlDataSource();
                    ((MysqlDataSource)dataSourse).setURL(URL);
                    ((MysqlDataSource)dataSourse).setUser(USERNAME);
                    ((MysqlDataSource)dataSourse).setPassword(PASSWORD);
                }
            }
        }
        return dataSourse;
    }

    //建立连接
    public  static Connection getConnection(){
        try {
            //内置了数据库连接池
            return getDataSourse().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //关闭连接
    public static void close(Connection connection, PreparedStatement statement, ResultSet resultSet){
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
