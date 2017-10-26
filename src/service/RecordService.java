package service;

import domain.Student;

import java.util.List;

/**
 * @Author:L1ANN
 * @Description:
 * @Date:Created in 15:15 2017/10/24
 * @Modified By:
 */
public interface RecordService {
    /**
     * 提供 获取查询总页数 的服务
     * @param ma_id 专业id
     * @param cl_id 班级id
     * @param record 每页的记录数
     * @return 总页数
     */
    public int getCount(int ma_id,int cl_id,int record);

    /**
     * 提供 获取查询页学生成绩记录 的服务
     * @param ma_id 专业id
     * @param cl_id 班级id
     * @param page 要查询的页数
     * @param record 每页的记录数
     * @return
     */
    public List<Student> recordPage(int ma_id,int cl_id, int page, int record);
}
