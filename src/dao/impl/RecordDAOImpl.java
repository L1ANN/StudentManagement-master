package dao.impl;

import dao.RecordDAO;
import domain.Student;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import util.BaseUtils;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author:L1ANN
 * @Description:
 * @Date:Created in 15:08 2017/10/24
 * @Modified By:
 */
public class RecordDAOImpl implements RecordDAO {
    private QueryRunner query = null;
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private Statement st = null;
    private ResultSet res = null;

    @Override
    public int getCount(String sql, int record) {
        query = BaseUtils.getQueryRunner();
        int count = -1;
        try {
             Object obj = query.query(sql,new ScalarHandler());
             long c = (long)obj;
             count = (int)c;
             count = (int)Math.ceil(1.0*count/record);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<Student> recordPage(String sql) {
        query = BaseUtils.getQueryRunner();
        List<Student> students = new ArrayList<Student>();
        try {
            students = query.query(sql, new BeanListHandler<Student>(Student.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
}
