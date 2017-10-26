package dao.impl;

import dao.ApartmentDAO;
import domain.Apartment;
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
 * @Date:Created in 12:17 2017/10/22
 * @Modified By:
 */
public class ApartmentDAOImpl implements ApartmentDAO {
    private QueryRunner query = null;
    private Connection conn = null;
    private Statement st = null;
    private PreparedStatement stmt = null;
    private ResultSet res = null;

    @Override
    public int getCount(String sql, int record) {
        conn = DBUtil.getConnection();
        int count = -1;
        try {
            st = conn.createStatement();
            res = st.executeQuery(sql);
            while (res.next()) {
                count = res.getInt(1);
                count = (int) Math.ceil(1.0 * count / record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll_st(res, st, conn);
        }
        return count;
    }

    @Override
    public List<Apartment> apartmentPage(String sql) {
        query = BaseUtils.getQueryRunner();
        List<Apartment> apartments = new ArrayList<Apartment>();
        try {
            apartments = query.query(sql, new BeanListHandler<Apartment>(Apartment.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return apartments;
    }

    @Override
    public Apartment selectApartmentById(int id) {
        query = BaseUtils.getQueryRunner();
        Apartment apartment = new Apartment();
        String sql = "select * from apartment join building where apartment.bu_id=building.bu_id and ap_id=?";
        try {
            apartment = query.query(sql, new BeanHandler<Apartment>(Apartment.class), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return apartment;
    }

    @Override
    public List<Student> selectStudentById(int id) {
        query = BaseUtils.getQueryRunner();
        List<Student> students = new ArrayList<>();
        String sql = "select student.stu_name,student.stu_num from student join ap_stu where student.stu_num=ap_stu.stu_num and ap_id=?";
        try {
            students = query.query(sql, new BeanListHandler<Student>(Student.class), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public List<Student> selectStudentNoApartment() {
        query = BaseUtils.getQueryRunner();
        String sql = "select * from student where stu_num not in " +
                "(select student.stu_num from student join ap_stu where student.stu_num=ap_stu.stu_num)";
        List<Student> students = new ArrayList<>();
        try {
            students = query.query(sql, new BeanListHandler<Student>(Student.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public boolean deleteStudent(Object[] nums) {
        //删除宿舍人员：宿舍_人员关联表删除记录，对应宿舍人数-1

        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);
            st = conn.createStatement();
            for (int i = 0; i < nums.length; i++) {
                st.addBatch("update apartment set ap_total=ap_total-1 where ap_id=(select ap_id from ap_stu where stu_num=" + nums[i] + ")");
                st.addBatch("delete from ap_stu where stu_num=" + nums[i]);

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
    public boolean addStudent(int stu_num, int ap_id) {
        //添加宿舍人员：宿舍_人员管理表增加记录，对应宿舍人数+1
        String sql1 = String.format("insert into ap_stu(ap_id,stu_num)values(%d,%d)", ap_id, stu_num);
        String sql2 = String.format("update apartment set ap_total=ap_total+1 where ap_id=%d", ap_id);
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
