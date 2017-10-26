package dao.impl;

import dao.MajorDAO;
import domain.Major;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import util.BaseUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author:L1ANN
 * @Description:
 * @Date:Created in 16:23 2017/10/19
 * @Modified By:
 */
public class MajorDAOImpl implements MajorDAO {
    QueryRunner query = null;

    @Override
    public List<Major> selectMajorByDepartment(int id) {
        query = BaseUtils.getQueryRunner();
        String sql = "select * from major where de_id=?";
        try {
            return query.query(sql, new BeanListHandler<Major>(Major.class), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
