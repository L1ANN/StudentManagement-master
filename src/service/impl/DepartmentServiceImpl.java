package service.impl;

import dao.DepartmentDAO;
import dao.impl.DepartmentDAOImpl;
import domain.Department;
import service.DepartmentService;

import java.util.List;

/**
 * @Author:L1ANN
 * @Description:
 * @Date:Created in 21:27 2017/10/19
 * @Modified By:
 */
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentDAO departmentDAO = new DepartmentDAOImpl();

    public List<Department> selectAllDepartment() {
        return departmentDAO.slectAllDepartment();
    }
}
