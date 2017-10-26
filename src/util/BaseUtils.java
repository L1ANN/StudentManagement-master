package util;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;

/**
 * @Author:L1ANN
 * @Description: c3p0和QueryRunner结合的数据库工具类
 * @Date:Created in 15:16 2017/10/17
 * @Modified By:
 */
public class BaseUtils {

    //初始化c3p0
    private static ComboPooledDataSource dataSource = null;
    static{
        //自动加载src目录下的c3p0配置文件
        dataSource = new ComboPooledDataSource();
    }

    /**
     *@Author:L1ANN
     *@Method getQueryRunner
     *@Description 获取QueryRunner对象
     *@Date:15:26 2017/10/17
     *
     *@param
     *@return 返回QueryRunner对象
    */
    public static QueryRunner getQueryRunner(){
        //创建QueryRunner对象，传入连接池对象。在创建QueryRunner对象时，如果传入对象时dataSource，
        //那么在使用QueryRunner对象的方法时，就不需要传入Connection对象
        QueryRunner query = new QueryRunner(dataSource);
        //自动从数据源中获取连接（不用关闭连接）
        return query;
    }

    /**
     *@Author:L1ANN
     *@Method addUpdateDelete
     *@Description 实现增/删/改的公共方法，需要参数
     *@Date:15:27 2017/10/17
     *
     *@param sql SQL语句
     *@param arr 参数的数组
     *@return 增/删/改的结果
    */
    public static boolean addUpdataDelete(String sql,Object[] arr){
        QueryRunner query = getQueryRunner();
        int count ;
        try{
            count = query.update(sql,arr);
            if(count > 0) return true;
            else return false;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 实现增/删/改的公共方法，不需要参数
     * @param sql
     * @return
     */
    public static boolean addUpdataDelete_NoArr(String sql){
        QueryRunner query = getQueryRunner();
        int count ;
        try{
            count = query.update(sql);
            if(count > 0) return true;
            else return false;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }


}
