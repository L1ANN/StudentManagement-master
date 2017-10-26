package dao;

import domain.Department;

import java.util.List;

/**
 * @Author:L1ANN
 * @Description: 对系别表（department）操作的DAO层
 * @Date:Created in 21:14 2017/10/19
 * @Modified By:
 */
public interface DepartmentDAO {

    /**
     * 获取所有系别
     * @return 系别集合
     */
    public List<Department> slectAllDepartment();
}
