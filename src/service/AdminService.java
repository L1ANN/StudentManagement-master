package service;

import domain.Admin;

/**
 * @Author:L1ANN
 * @Description: 对管理员表(admin)操作的服务层
 * @Date:Created in 13:16 2017/10/17
 * @Modified By:
 */
public interface AdminService {


    /**
     * 提供登录服务
     * @param name 用户名
     * @param password 密码
     * @return
     */
    public Admin login(String name, int password);

    /**
     * 修改管理员密码
     * @param name 用户名
     * @param newpass 新密码
     * @return 修改结果
     */
    public boolean changePass(String name,int newpass);
}
