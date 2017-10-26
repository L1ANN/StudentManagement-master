package dao.impl;

import dao.DepartmentDAO;
import domain.Department;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import util.BaseUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author:L1ANN
 * @Description:
 * @Date:Created in 21:17 2017/10/19
 * @Modified By:
 */
public class DepartmentDAOImpl implements DepartmentDAO {
    private QueryRunner query = null;

    @Override
    public List<Department> slectAllDepartment() {
        query = BaseUtils.getQueryRunner();

        String sql = "select * from department";
        try {
            return query.query(sql, new BeanListHandler<Department>(Department.class));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
