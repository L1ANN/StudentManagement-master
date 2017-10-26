package util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @Author:L1ANN
 * @Description: JDBC数据库工具类
 * @Date:Created in 11:00 2017/10/18
 * @Modified By:
 */
public class DBUtil {
    private static String url;
    private static String user;
    private static String pwd;
    private static String driver;

    static {
        try {
            Properties prop = new Properties();
            InputStream is = DBUtil.class.getClassLoader().getResourceAsStream(
                    "db.properties");
            prop.load(is);
            is.close();
            driver = prop.getProperty("jdbc.driver");
            url = prop.getProperty("jdbc.url");
            user = prop.getProperty("jdbc.user");
            pwd = prop.getProperty("jdbc.password");

            Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //从连接池中获取数据库连接
    public static Connection getConnection() {
        Connection conn = null;
        try {

            conn = DriverManager.getConnection(url, user, pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static boolean update(String sql1, String sql2) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs =null;
        int[] result;
        try {
            conn = getConnection();
            //对于同时有多条增/删/改操作时，需要开启事务
            conn.setAutoCommit(false);
            st = conn.createStatement();
            st.addBatch(sql1);
            st.addBatch(sql2);
            result = st.executeBatch();
            //上面SQL语句成功之后就通知数据库提交事务
            conn.commit();
            if (result[0] == 1 && result[1] == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            try {
                //捕获异常会手动通知数据库执行回滚事务操作
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            DBUtil.closeAll_st(rs,st, conn);
        }
        return false;
    }

    //按顺序关闭所有连接
    public static void closeAll(ResultSet res, PreparedStatement stmt, Connection conn) {
        try {
            if (res != null) res.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            res = null;
            stmt = null;
            conn = null;
        }
    }

    //按顺序关闭所有连接
    public static void closeAll_st(ResultSet res, Statement st, Connection conn) {
        try {
            if (res != null) res.close();
            if (st != null) st.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            res = null;
            st = null;
            conn = null;
        }
    }
}
