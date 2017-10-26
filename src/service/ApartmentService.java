package service;

import domain.Apartment;
import domain.Student;

import java.util.List;

/**
 * @Author:L1ANN
 * @Description:
 * @Date:Created in 12:42 2017/10/22
 * @Modified By:
 */
public interface ApartmentService {
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
    public List<Apartment> apartmentPage(String name, String value, int page, int record);

    /**
     * 提供 根据宿舍id获取宿舍对象 的服务
     * @param id 宿舍id
     * @return 宿舍对象
     */
    public Apartment selectApartmentById(int id);

    /**
     *  提供 根据宿舍id获取宿舍人员 的服务
     * @param id 宿舍id
     * @return 宿舍人员
     */
    public List<Student> selectStudentById(int id);

    public List<Student> selectStudentNoApartment();

    /**
     *  提供 根据给的新生准考证号删除宿舍人员 的服务
     * @param nums 新生准考证号数组
     * @return 删除结果
     */
    public boolean deleteStudent(Object[] nums);

    /**
     * 提供 获取无宿舍人员总页面 的服务
     * @param record 每页的记录数
     * @return 总页数
     */
    public int getCount(int record);

    /**
     * 提供 获取无宿舍人员集合 的服务
     * @param page 要查询的页数
     * @param record 每页的记录数
     * @return 本页的无宿舍人员集合
     */
    public List<Student> noApartmentPage(int page,int record);

    /**
     * 提供 添加宿舍人员 的服务
     * @param stu_num 新生准考证号
     * @param ap_id 宿舍id
     * @return 添加结果
     */
    public boolean addStudent(int stu_num,int ap_id);
}
