package service.impl;

import dao.ClassDAO;
import dao.StudentDAO;
import dao.impl.ClassDAOImpl;
import dao.impl.StudentDAOImpl;
import domain.Class;
import domain.Student;
import service.ClassService;
import sun.plugin2.message.Message;

import java.text.MessageFormat;
import java.util.List;

/**
 * @Author:L1ANN
 * @Description:
 * @Date:Created in 10:47 2017/10/20
 * @Modified By:
 */
public class ClassServiceImpl implements ClassService {
    private ClassDAO classDAO = new ClassDAOImpl();
    private StudentDAO studentDAO = new StudentDAOImpl();

    @Override
    public int getCount(String name, String value, int record) {
        String sql = "";
        if (name != null && name != "") {
            sql = MessageFormat.format("select count(*) from class join major join department " +
                    "where  class.ma_id=major.ma_id and major.de_id = department.de_id and {0} like ''%{1}%''", name, value);
        } else {
            sql = "select count(*) from class";
        }
        return classDAO.getCount(sql, record);
    }

    @Override
    public List<Class> classPage(String name, String value, int page, int record) {
        String sql = "";
        if (name != null && name != "") {
            sql = MessageFormat.format("select cl_id,cl_name,ma_name,de_name from class join major join department " +
                    "where  class.ma_id=major.ma_id and major.de_id = department.de_id and {0} like ''%{1}%'' limit {2},{3}", name, value, (page - 1) * record, record);
        } else {
            sql = MessageFormat.format("select cl_id,cl_name,ma_name,de_name from class join major join department " +
                    "where  class.ma_id=major.ma_id and major.de_id = department.de_id limit {0},{1}", (page - 1) * record, record);
        }
        return classDAO.classPage(sql);
    }

    @Override
    public List<Class> selectClassByMajor(int id) {
        return classDAO.selectClassByMajor(id);

    }

    @Override
    public Class selectClassById(int id) {
        return classDAO.selectClassById(id);
    }

    @Override
    public List<Student> selectStudentById(int id) {
        return classDAO.selectStudentById(id);
    }

    @Override
    public boolean deleteStudent(Object[] nums) {
        return classDAO.deleteStudent(nums);
    }

    @Override
    public int getCount(int record) {
        String sql = "select count(*) from student where stu_num not in (select stu_num from cl_stu)";
        return studentDAO.getCount(sql, record);
    }

    @Override
    public List<Student> noClassPage(int page, int record) {
        String sql = MessageFormat.format("select * from student where stu_num not in (select stu_num from cl_stu) limit {0},{1}", (page - 1) * record, record);
        return studentDAO.studentPage(sql);
    }

    @Override
    public boolean addStudent(int stu_num, int cl_id) {
        return classDAO.addStudent(stu_num, cl_id);
    }
}
