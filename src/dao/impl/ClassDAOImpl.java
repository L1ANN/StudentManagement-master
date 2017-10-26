package dao.impl;

import dao.ClassDAO;
import domain.Apartment;
import domain.Class;
import domain.Student;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import util.BaseUtils;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author:L1ANN
 * @Description:
 * @Date:Created in 10:36 2017/10/20
 * @Modified By:
 */
public class ClassDAOImpl implements ClassDAO {
    private QueryRunner query = null;
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private Statement st = null;
    private ResultSet res = null;

    @Override
    public int getCount(String sql, int record) {
        conn = DBUtil.getConnection();
        int count = -1;
        try {
            st = conn.createStatement();
            res = st.executeQuery(sql);
            if (res.next()) {
                count = res.getInt(1);
                count = (int) Math.ceil(1.0 * count / record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<Class> classPage(String sql) {
        query = BaseUtils.getQueryRunner();
        List<Class> classes = new ArrayList<>();
        try {
            classes = query.query(sql, new BeanListHandler<Class>(Class.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classes;
    }

    @Override
    public List<Class> selectClassByMajor(int id) {
        query = BaseUtils.getQueryRunner();
        String sql = "select * from class where ma_id=?";
        try {
            return query.query(sql, new BeanListHandler<Class>(Class.class), id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Class selectClassById(int id) {
        query = BaseUtils.getQueryRunner();
        Class classes = new Class();
        String sql = "select cl_id,cl_name,ma_name,de_name from class join major join department " +
                "where  class.ma_id=major.ma_id and major.de_id = department.de_id and cl_id=?";
        try {
            classes = query.query(sql, new BeanHandler<Class>(Class.class), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classes;
    }

    @Override
    public List<Student> selectStudentById(int id) {
        query = BaseUtils.getQueryRunner();
        List<Student> students = new ArrayList<>();
        String sql = "select student.stu_name,student.stu_num from student join cl_stu where student.stu_num=cl_stu.stu_num and cl_id=?";
        try {
            students = query.query(sql, new BeanListHandler<Student>(Student.class), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public boolean deleteStudent(Object[] nums) {
        //删除班级人员：班级_人员关联表删除记录，新生表中班级删除

        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);
            st = conn.createStatement();
            for (int i = 0; i < nums.length; i++) {
                st.addBatch("update student set stu_class='' where stu_num=" + nums[i]);
                st.addBatch("delete from cl_stu where stu_num=" + nums[i]);

            }
            st.executeBatch();
            conn.commit();
            return true;
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            DBUtil.closeAll_st(res, st, conn);
        }
        return false;
    }

    @Override
    public boolean addStudent(int stu_num, int cl_id) {
        //添加班级人员：班级_人员管理表增加记录，新生表更新stu_class字段
        String sql1 = String.format("insert into cl_stu(cl_id,stu_num) values(%d,%d)", cl_id, stu_num);
        String sql2 = String.format("update student set stu_class=(select cl_name from class where cl_id=%d) where stu_num=%d", cl_id, stu_num);
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);
            st = conn.createStatement();
            st.addBatch(sql1);
            st.addBatch(sql2);
            st.executeBatch();
            conn.commit();
            return true;
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            DBUtil.closeAll_st(res, st, conn);
        }
        return false;
    }
}
