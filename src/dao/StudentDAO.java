package dao;

import domain.Student;

import java.util.List;

/**
 * @Author:L1ANN
 * @Description: 对新生表(student)操作的数据访问层
 * @Date:Created in 10:13 2017/10/18
 * @Modified By:
 */
public interface StudentDAO {
    /**
     * 获取总页面
     *
     * @param sql    拼接好的SQL语句
     * @param record 每页的页数
     * @return 总页数
     */
    public int getCount(String sql, int record);

    /**
     * 新生表分页查询的方法
     *
     * @param sql 拼接好的SQL语句
     * @return 本页要显示的新生集合
     */
    public List<Student> studentPage(String sql);


    public boolean addStudent(List<Student> stu);
    /**
     *
     * @param num
     * @return
     */
    public Student selectStudentByNum(int num);

    /**
     * 修改新生记录
     *
     * @param stu 要修改的新生对象
     * @return 返回修改的结果
     */
    public boolean updateStudent(Student stu);

    /**
     * 删除新生记录
     *
     * @param num 要删除的考生的准考证号
     * @return 返回删除的结果
     */
    public boolean deleteStudent(int num);

    /**
     * 调整新生专业
     * @param stu_num 新生准考证号
     * @param de_id 要修改的新生的系别id
     * @param ma_id 要修改的新生的专业id
     * @return 返回修改结果
     */
    public boolean updateMajor(int stu_num,int de_id,int ma_id);

    /**
     * 调整新生班级
     * @param stu_num 新生准考证号
     * @param cl_id 要修改的新生的班级id
     * @return 返回修改结果
     */
    public boolean updateClass(int stu_num,int cl_id);

    /**
     * 根据新生准考证号得到对应的专业id
     * @param stu_num 新生准考证号
     * @return 新生专业id
     */
    public int getMajorByNum(int stu_num);


}
