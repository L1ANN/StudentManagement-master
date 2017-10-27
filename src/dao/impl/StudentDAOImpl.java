package dao.impl;

import dao.StudentDAO;
import domain.Student;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import util.BaseUtils;
import util.DBUtil;

import java.sql.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author:L1ANN
 * @Description:
 * @Date:Created in 10:43 2017/10/18
 * @Modified By:
 */
public class StudentDAOImpl implements StudentDAO {
    private Connection conn = null;
    private Statement st = null;
    private PreparedStatement stmt = null;
    private ResultSet res = null;

    @Override
    public int getCount(String sql, int record) {
        conn = DBUtil.getConnection();
        int count = -1;
        try {
            stmt = conn.prepareStatement(sql);
            res = stmt.executeQuery();
            if (res.next()) {
                count = res.getInt(1);
                count = (int) Math.ceil(1.0 * count / record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(res, stmt, conn);
        }
        return count;
    }

    @Override
    public List<Student> studentPage(String sql) {
        QueryRunner query = BaseUtils.getQueryRunner();
        List<Student> students = new ArrayList<Student>();
        try {
            return query.query(sql, new BeanListHandler<Student>(Student.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public boolean addStudent(List<Student> students) {
        String sql1 = "";
        String sql2 = "";
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);
            st = conn.createStatement();
            Iterator<Student> it = students.iterator();
            while (it.hasNext()) {
                Student stu = it.next();
                sql1 = String.format("insert into student(stu_num,stu_name,stu_age,stu_gender,stu_ethnic,stu_native,stu_time,stu_birth,stu_phone,stu_address,stu_tuition)" +
                        " values(%d,'%s',%d,'%s','%s','%s',%d,'%s','%s','%s',%f)", stu.getStu_num(), stu.getStu_name(), stu.getStu_age(), stu.getStu_gender(), stu.getStu_ethnic(), stu.getStu_native(), stu.getStu_time(), (stu.getStu_birth()).toString(), stu.getStu_phone(), stu.getStu_address(), stu.getStu_tuition());
                sql2 = String.format("insert into record(stu_num,re_eng,re_pol,re_math,re_pro,re_total) values(%d,%f,%f,%f,%f,%f)", stu.getStu_num(), stu.getRe_eng(), stu.getRe_pol(), stu.getRe_math(), stu.getRe_pro(), stu.getRe_total());
                st.addBatch(sql1);
                st.addBatch(sql2);
            }
            st.executeBatch();
            conn.commit();
            return true;
        } catch (SQLException e) {
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
    public Student selectStudentByNum(int num) {
        QueryRunner query = BaseUtils.getQueryRunner();
        String sql = "select * from student where stu_num=?";
        Student stu = new Student();
        try {
            stu = query.query(sql, new BeanHandler<Student>(Student.class), num);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stu;
    }

    @Override
    public boolean updateStudent(Student stu) {
        String sql = "update student set stu_name=?,stu_age=?,stu_gender=?,stu_ethnic=?,stu_native=?,stu_time=?" +
                ",stu_birth=?,stu_phone=?,stu_address=? where stu_num=?";
        List<Object> list = new ArrayList<>();
        if (stu != null) {
            list.add(stu.getStu_name());
            list.add(stu.getStu_age());
            list.add(stu.getStu_gender());
            list.add(stu.getStu_ethnic());
            list.add(stu.getStu_native());
            list.add(stu.getStu_time());
            list.add(stu.getStu_birth());
            list.add(stu.getStu_phone());
            list.add(stu.getStu_address());
            list.add(stu.getStu_num());
        }
        try {
            return BaseUtils.addUpdataDelete(sql, list.toArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteStudent(int num) {

        //删除新生记录：对应的寝室人数-1，学生表删除，专业_学生关联表删除，班级_学生关联表删除，寝室_学生关联表删除,成绩表删除
        String sql5 = "update apartment set ap_total=ap_total-1 where ap_id = (select ap_id from ap_stu where stu_num = " + num + ")";
        String sql1 = "delete from student where stu_num=" + num;
        String sql2 = "delete from ma_stu where stu_num=" + num;
        String sql3 = "delete from cl_stu where stu_num=" + num;
        String sql4 = "delete from ap_stu where stu_num=" + num;
        String sql6 = "delete from record where stu_num=" + num;

        try {
            conn = DBUtil.getConnection();
            //对于同时有多条增/删/改操作时，需要开启事务
            conn.setAutoCommit(false);
            st = conn.createStatement();
            st.addBatch(sql5);
            st.addBatch(sql1);
            st.addBatch(sql2);
            st.addBatch(sql3);
            st.addBatch(sql4);
            st.addBatch(sql5);
            st.addBatch(sql6);
            st.executeBatch();
            //上面SQL语句成功之后就通知数据库提交事务
            conn.commit();
            return true;

        } catch (Exception e) {
            try {
                //捕获异常会手动通知数据库执行回滚事务操作
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
    public boolean updateMajor(int stu_num, int de_id, int ma_id) {
        //修改新生专业，要更新两处：新生表和专业_新生关联表
        conn = DBUtil.getConnection();
        String sql = "select count(*) from ma_stu where stu_num=?";
        int count = -1;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,stu_num);
            res=stmt.executeQuery();
            if(res.next()){
                count = res.getInt(1);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        String sql2 = "";
        String sql1 = String.format("update student set stu_department=(select de_name from department where de_id=%d),stu_major=(select ma_name from major where ma_id=%d) where stu_num=%d",
                de_id, ma_id, stu_num);
        if(count==1) {
            sql2 = String.format("update ma_stu set ma_id=%d where stu_num=%d", ma_id, stu_num);
        }else{
            sql2=String.format("insert into ma_stu(ma_id,stu_num) values(%d,%d)",ma_id,stu_num);
        }
        return DBUtil.update(sql1, sql2);

    }

    @Override
    public boolean updateClass(int stu_num, int cl_id) {
        //修改/添加新生班级，要更新两处：新生表和班级_新生关联表（要先判断，原来是否存在，存在更新，不存在插入）
        conn = DBUtil.getConnection();
        String sql = "select count(*) from cl_stu where stu_num=?";
        int count = -1;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,stu_num);
            res=stmt.executeQuery();
            if(res.next()){
                count = res.getInt(1);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        String sql2 = "";
        String sql1 = String.format("update student set stu_class=(select cl_name from class where cl_id=%d) where stu_num=%d", cl_id, stu_num);
        if(count==1) {
            sql2 = String.format("update cl_stu set cl_id=%d where stu_num=%d", cl_id, stu_num);
        }else{
            sql2 = String.format("insert into cl_stu(cl_id,stu_num) values(%d,%d)",cl_id,stu_num);
        }
        return DBUtil.update(sql1, sql2);
    }

    @Override
    public int getMajorByNum(int stu_num) {
        String sql = "select ma_id from ma_stu where stu_num=?";
        int ma_id = -1;
        try {
            conn = DBUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, stu_num);
            res = stmt.executeQuery();
            if (res.next()) {
                ma_id = res.getInt(1);
                return ma_id;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ma_id;
    }
}
