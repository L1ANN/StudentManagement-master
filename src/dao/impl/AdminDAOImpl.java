package dao.impl;

import dao.AdminDAO;
import domain.Admin;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import util.BaseUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author:L1ANN
 * @Description: AdminDAO的实现类
 * @Date:Created in 13:14 2017/10/17
 * @Modified By:
 */
public class AdminDAOImpl implements AdminDAO {
    @Override
    public Admin find(String name, int password) {
        QueryRunner query = BaseUtils.getQueryRunner();
        String sql = "select * from admin where ad_name=? and ad_pass=?";
        Admin admin = null;
        try {

            admin = query.query(sql, new BeanHandler<Admin>(Admin.class), name, password);
            return admin;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return admin;
    }

    @Override
    public boolean changePass(String name, int newpass) {

        String sql = "update admin set ad_pass=? where ad_name=?";
        List<Object> arr = new ArrayList<>();
        try{
            arr.add(newpass);
            arr.add(name);
            return BaseUtils.addUpdataDelete(sql,arr.toArray());
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
