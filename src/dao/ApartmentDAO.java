package dao;

import domain.Apartment;
import domain.Student;

import java.util.List;

/**
 * @Author:L1ANN
 * @Description: 对宿舍表（apartment）操作的DAO层
 * @Date:Created in 11:57 2017/10/22
 * @Modified By:
 */
public interface ApartmentDAO {
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
    public List<Apartment> apartmentPage(String sql);

    /**
     *  根据宿舍id获取宿舍对象
     * @param id 宿舍id
     * @return 宿舍对象
     */
    public Apartment selectApartmentById(int id);

    /**
     *  根据宿舍id获取宿舍人员集合
     * @param id 宿舍id
     * @return 宿舍人员集合
     */
    public List<Student> selectStudentById(int id);

    /**
     *  获取没有宿舍的新生集合
     * @return 没有宿舍的新生集合
     */
    public List<Student> selectStudentNoApartment();

    /**
     *  删除宿舍人员
     * @param nums 要删除新生的准考证号数组
     * @return 删除结果
     */
    public boolean deleteStudent(Object[] nums);

    /**
     *  添加宿舍人员
     * @param stu_num 要添加的新生准考证号
     * @return 删除结果
     */
    public boolean addStudent(int stu_num,int ap_id);


}
