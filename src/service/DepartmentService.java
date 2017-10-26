package service;

import dao.DepartmentDAO;
import dao.impl.DepartmentDAOImpl;
import domain.Department;

import java.util.List;

/**
 * @Author:L1ANN
 * @Description: 对系别表（department）操作的服务层
 * @Date:Created in 21:23 2017/10/19
 * @Modified By:
 */
public interface DepartmentService {

    /**
     * 提供获取所有系别的服务
     * @return 系别集合
     */
    public List<Department> selectAllDepartment();
}
