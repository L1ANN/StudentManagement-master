package dao;

import domain.Apartment;
import domain.Class;
import domain.Student;

import java.util.List;

/**
 * @Author:L1ANN
 * @Description: 对班级表（class）操作的DAO层
 * @Date:Created in 10:33 2017/10/20
 * @Modified By:
 */
public interface ClassDAO {
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
    public List<Class> classPage(String sql);
    /**
     * 根据专业id获取所有班级
     * @param id 专业id
     * @return 班级集合
     */
    public List<Class> selectClassByMajor(int id);
    /**
     *  根据班级id获取班级对象
     * @param id 班级id
     * @return 班级对象
     */
    public Class selectClassById(int id);

    /**
     *  根据班级id获取班级人员集合
     * @param id 班级id
     * @return 班级人员集合
     */
    public List<Student> selectStudentById(int id);

    /**
     *  删除班级人员
     * @param nums 要删除新生的准考证号数组
     * @return 删除结果
     */
    public boolean deleteStudent(Object[] nums);

    /**
     *  添加班级人员
     * @param stu_num 要添加的新生准考证号
     * @return 删除结果
     */
    public boolean addStudent(int stu_num,int ap_id);
}
