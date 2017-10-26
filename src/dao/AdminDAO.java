package dao;

import domain.Admin;

/**
 * @Author:L1ANN
 * @Description: 对管理员表(admin)操作的DAO层
 * @Date:Created in 13:13 2017/10/17
 * @Modified By:
 */
public interface AdminDAO {


    /**
     * 根据用户名和密码来查找管理员
     * @param name 用户名
     * @param password 密码
     * @return 管理员对象
     */
    public Admin find(String name, int password);

    /**
     * 修改管理员密码
     * @param name 用户名
     * @param newpass 新密码
     * @return 修改结果
     */
    public boolean changePass(String name,int newpass);
}
