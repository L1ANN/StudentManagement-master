package service.impl;

import dao.StudentDAO;
import dao.impl.StudentDAOImpl;
import domain.Student;
import service.StudentService;

import java.text.MessageFormat;
import java.util.List;

/**
 * @Author:L1ANN
 * @Description: StudentService的实现类
 * @Date:Created in 12:14 2017/10/18
 * @Modified By:
 */
public class StudentServiceImpl implements StudentService {
    private StudentDAO studentDAO = new StudentDAOImpl();

    @Override
    public int getCount(String name, String value, int record) {
        String sql = null;
        //如果有参数值，根据条件模糊查询
        if (name != null && name != "") {
            sql = MessageFormat.format("select count(*) from student where {0} like ''%{1}%''", name, value);
        }
        //否则，查询全部
        else {
            sql = "select count(*) from student";

        }
        return studentDAO.getCount(sql, record);

    }

    @Override
    public List<Student> studentPage(String name, String value, int page, int record) {
        String sql = null;
        //如果有参数值，根据条件模糊查询
        if (name != null && name != "") {
            sql = MessageFormat.format("select * from student where {0} like ''%{1}%'' limit {2},{3}", name, value, (page - 1) * record, record);

        }
        //否则，查询全部
        else {
            sql = MessageFormat.format("select * from student limit {0},{1}", (page - 1) * record, record);
        }

        return studentDAO.studentPage(sql);
    }

    @Override
    public boolean addStudent(List<Student> students) {
        return studentDAO.addStudent(students);
    }

    @Override
    public Student selectStudentByNum(int num) {
        return studentDAO.selectStudentByNum(num);
    }

    @Override
    public boolean updateStudent(Student student) {
        return studentDAO.updateStudent(student);
    }

    @Override
    public boolean deleteStudent(int num) {
        return studentDAO.deleteStudent(num);
    }

    @Override
    public boolean updateMajor(int stu_num, int de_id, int ma_id) {
        return studentDAO.updateMajor(stu_num, de_id, ma_id);

    }

    @Override
    public boolean updateClass(int stu_num, int cl_id) {
        return studentDAO.updateClass(stu_num, cl_id);
    }

    @Override
    public int getMajorByNum(int stu_num) {
        return studentDAO.getMajorByNum(stu_num);
    }
}
