package service;

import domain.Student;

import java.util.List;

/**
 * @Author:L1ANN
 * @Description: 对新生表(student)操作的服务层
 * @Date:Created in 11:53 2017/10/18
 * @Modified By:
 */
public interface StudentService {

    /**
     * 提供 获取查询总页数 的服务
     * @param name SQL字段名
     * @param value SQL字段值
     * @param record 每页的记录数
     * @return 总页数
     */
    public int getCount(String name,String value,int record);

    /**
     * 提供 获取查询页学生记录 的服务
     * @param name SQL字段名
     * @param value SQL字段值
     * @param page 要查询的页数
     * @param record 每页的记录数
     * @return
     */
    public List<Student> studentPage(String name,String value,int page,int record);

    public boolean addStudent(List<Student> students);
    /**
     * 根据准考证号查询学生记录
     * @param num 学生准考证号
     * @return 查询到的学生对象
     */
    public Student selectStudentByNum(int num);
    /**
     * 提供新生信息修改服务
     * @param student 要修改的新生对象
     * @return 修改的结果
     */
    public boolean updateStudent(Student student);

    /**
     * 提供删除新生服务
     * @param num 要删除的新生准考证号
     * @return 删除的结果
     */
    public boolean deleteStudent(int num);

    /**
     * 提供新生专业修改服务
     * @param stu_num 要修改的新生准考证号
     * @param de_id 要修改新生的系别id
     * @param ma_id 要修改新生的专业id
     * @return 修改的结果
     */
    public boolean updateMajor(int stu_num,int de_id,int ma_id);

    /**
     * 提供新生班级修改服务
     * @param stu_num 要修改的新生准考证号
     * @param cl_id 要修改新生的专业id
     * @return 修改结果
     */
    public boolean updateClass(int stu_num,int cl_id);

    /**
     * 提供获取新生专业id服务
     * @param stu_num 新生准考证号
     * @return 新生专业id
     */
    public int getMajorByNum(int stu_num);

}
