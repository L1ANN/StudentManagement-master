package service.impl;

import dao.RecordDAO;
import dao.impl.RecordDAOImpl;
import domain.Student;
import service.RecordService;

import java.text.MessageFormat;
import java.util.List;

/**
 * @Author:L1ANN
 * @Description:
 * @Date:Created in 15:18 2017/10/24
 * @Modified By:
 */
public class RecordServiceImpl implements RecordService {
    private RecordDAO recordDAO = new RecordDAOImpl();

    @Override
    public int getCount(int ma_id, int cl_id, int record) {
        String sql = "";
        //没有选择班级，查询全专业
        if (cl_id == -1) {
            sql = MessageFormat.format("select count(*) from student join record join ma_stu join cl_stu " +
                    "where student.stu_num=record.stu_num and student.stu_num=ma_stu.stu_num and student.stu_num=cl_stu.stu_num and ma_id={0}", ma_id);
        }
        //选择班级，查询班级
        else {
            sql = MessageFormat.format("select count(*) from student join record join ma_stu join cl_stu " +
                    "where student.stu_num=record.stu_num and student.stu_num=ma_stu.stu_num and student.stu_num=cl_stu.stu_num and cl_id={0}", cl_id);
        }
        return recordDAO.getCount(sql, record);
    }

    @Override
    public List<Student> recordPage(int ma_id, int cl_id, int page, int record) {
        String sql = "";
        //没有选择班级，查询全专业,按分数降序排列
        if (cl_id == -1) {
            sql = MessageFormat.format("select student.stu_num,stu_name,stu_department,stu_major,stu_class,re_eng,re_pol,re_math,re_pro,re_total,ma_id,cl_id from student join record join ma_stu join cl_stu " +
                    "where student.stu_num=record.stu_num and student.stu_num=ma_stu.stu_num and student.stu_num=cl_stu.stu_num and ma_id={0} order by re_total desc limit {1},{2} ", ma_id, (page - 1) * record, record);
        }
        //选择班级，查询班级，按分数降序排列
        else {
            sql = MessageFormat.format("select student.stu_num,stu_name,stu_department,stu_major,stu_class,re_eng,re_pol,re_math,re_pro,re_total,ma_id,cl_id from student join record join ma_stu join cl_stu " +
                    "where student.stu_num=record.stu_num and student.stu_num=ma_stu.stu_num and student.stu_num=cl_stu.stu_num and cl_id={0}  order by re_total desc limit {1},{2}", cl_id, (page - 1) * record, record);
        }
        return recordDAO.recordPage(sql);
    }
}
