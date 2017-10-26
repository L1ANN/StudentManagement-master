package dao;

import domain.Record;
import domain.Student;

import java.util.List;

/**
 * @Author:L1ANN
 * @Description:
 * @Date:Created in 14:58 2017/10/24
 * @Modified By:
 */
public interface RecordDAO {

    /**
     * 获取总页面
     *
     * @param sql    拼接好的SQL语句
     * @param record 每页的页数
     * @return 总页数
     */
    public int getCount(String sql, int record);

    /**
     * 新生成绩表分页查询的方法
     *
     * @param sql 拼接好的SQL语句
     * @return 本页要显示的新生成绩
     */
    public List<Student> recordPage(String sql);
}
