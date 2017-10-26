package service.impl;

import dao.AdminDAO;
import dao.impl.AdminDAOImpl;
import domain.Admin;
import service.AdminService;

/**
 * @Author:L1ANN
 * @Description: AdminService的实现类
 * @Date:Created in 17:12 2017/10/17
 * @Modified By:
 */
public class AdminServiceImpl implements AdminService {
    private AdminDAO adminDAO = new AdminDAOImpl();

    @Override
    public Admin login(String name, int password) {
        return adminDAO.find(name, password);
    }

    @Override
    public boolean changePass(String name, int newpass) {
        return adminDAO.changePass(name,newpass);
    }
}
