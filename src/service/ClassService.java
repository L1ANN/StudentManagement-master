package service;

import domain.Apartment;
import domain.Class;
import domain.Student;

import java.util.List;

/**
 * @Author:L1ANN
 * @Description: 对班级表（class）操作的服务层
 * @Date:Created in 10:39 2017/10/20
 * @Modified By:
 */
public interface ClassService {
    /**
     * 提供 获取查询总页数 的服务
     * @param name SQL字段名
     * @param value SQL字段值
     * @param record 每页的记录数
     * @return 总页数
     */
    public int getCount(String name,String value,int record);

    /**
     * 提供 获取查询页宿舍记录 的服务
     * @param name SQL字段名
     * @param value SQL字段值
     * @param page 要查询的页数
     * @param record 每页的记录数
     * @return 本页的宿舍集合
     */
    public List<Class> classPage(String name, String value, int page, int record);

    /**
     * 提供 根据专业id获取班级列表 的服务
     * @param id
     * @return
     */
    public List<Class> selectClassByMajor(int id);

    /**
     * 提供 根据班级id获取班级对象 的服务
     * @param id 班级id
     * @return 班级对象
     */
    public Class selectClassById(int id);

    /**
     *  提供 根据班级id获取宿舍人员 的服务
     * @param id 班级id
     * @return 班级人员集合
     */
    public List<Student> selectStudentById(int id);

    /**
     *  提供 根据给的新生准考证号删除班级人员 的服务
     * @param nums 新生准考证号数组
     * @return 删除结果
     */
    public boolean deleteStudent(Object[] nums);

    /**
     * 提供 获取无班级人员总页面 的服务
     * @param record 每页的记录数
     * @return 总页数
     */
    public int getCount(int record);

    /**
     * 提供 获取无班级人员集合 的服务
     * @param page 要查询的页数
     * @param record 每页的记录数
     * @return 本页的无班级人员集合
     */
    public List<Student> noClassPage(int page,int record);

    /**
     * 提供 添加班级人员 的服务
     * @param stu_num 新生准考证号
     * @param cl_id 班级id
     * @return 添加结果
     */
    public boolean addStudent(int stu_num,int cl_id);
}
