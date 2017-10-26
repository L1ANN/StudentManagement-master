package service.impl;

import dao.ApartmentDAO;
import dao.StudentDAO;
import dao.impl.ApartmentDAOImpl;
import dao.impl.StudentDAOImpl;
import domain.Apartment;
import domain.Student;
import service.ApartmentService;

import java.text.MessageFormat;
import java.util.List;

/**
 * @Author:L1ANN
 * @Description:
 * @Date:Created in 12:46 2017/10/22
 * @Modified By:
 */
public class ApartmentServiceImpl implements ApartmentService {
    private ApartmentDAO apartmentDAO = new ApartmentDAOImpl();
    private StudentDAO studentDAO = new StudentDAOImpl();

    @Override
    public int getCount(String name, String value, int record) {
        String sql = null;
        //如果name不为空，根据关键字模糊条件查询
        if (name != null && name != "") {
            sql = MessageFormat.format("select count(*) from apartment join building where apartment.bu_id=building.bu_id and {0} like ''%{1}%''", name, value);
        }
        //否则，查询整个表
        else {
            sql = "select count(*) from apartment";
        }
        return apartmentDAO.getCount(sql, record);
    }

    @Override
    public List<Apartment> apartmentPage(String name, String value, int page, int record) {
        String sql = null;
        //如果name不为空，根据关键字模糊条件查询
        if (name != null && name != "") {
            sql = MessageFormat.format("select apartment.ap_id,apartment.bu_id,building.bu_name,apartment.ap_num,apartment.ap_total" +
                    " from apartment join building where apartment.bu_id = building.bu_id and {0} like ''%{1}%'' limit {2},{3}", name, value, (page - 1) * record, record);
        }
        //否则，查询整个表
        else {
            sql = MessageFormat.format("select apartment.ap_id,apartment.bu_id,building.bu_name,apartment.ap_num,apartment.ap_total " +
                    "from apartment join building where apartment.bu_id = building.bu_id limit {0},{1}", (page - 1) * record, record);
        }
        return apartmentDAO.apartmentPage(sql);
    }


    @Override
    public Apartment selectApartmentById(int id) {
        return apartmentDAO.selectApartmentById(id);
    }

    @Override
    public List<Student> selectStudentById(int id) {
        return apartmentDAO.selectStudentById(id);
    }

    @Override
    public List<Student> selectStudentNoApartment() {
        return apartmentDAO.selectStudentNoApartment();
    }

    @Override
    public boolean deleteStudent(Object[] nums) {
        return apartmentDAO.deleteStudent(nums);
    }

    @Override
    public int getCount(int record) {
        String sql = "select count(*) from student where stu_num not in " +
                "(select stu_num from ap_stu)";
        return studentDAO.getCount(sql, record);
    }

    @Override
    public List<Student> noApartmentPage(int page, int record) {
        String sql = MessageFormat.format("select * from student where stu_num not in " +
                "(select stu_num from ap_stu) limit {0},{1}", (page - 1) * record, record);
        return studentDAO.studentPage(sql);
    }

    @Override
    public boolean addStudent(int stu_num, int ap_id) {
        return apartmentDAO.addStudent(stu_num, ap_id);
    }
}
