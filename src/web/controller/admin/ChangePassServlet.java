package web.controller.admin;

import domain.Admin;
import service.AdminService;
import service.impl.AdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author:L1ANN
 * @Description:
 * @Date:Created in 22:39 2017/10/24
 * @Modified By:
 */
@WebServlet(name = "ChangePassServlet")
public class ChangePassServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdminService adminService = new AdminServiceImpl();
        Admin admin = (Admin)request.getSession().getAttribute("admin");
        String newpass = request.getParameter("newpass");
        int pass = -1;
        boolean result=false;
        String message= "";
        if(newpass!=null&&newpass!=""){
            pass=Integer.parseInt(newpass);
        }
        result = adminService.changePass(admin.getAd_name(),pass);
        if(result){
            message="修改成功！";
            request.setAttribute("message",message);
            request.getRequestDispatcher("/ad_changepass.jsp").forward(request,response);
        }else{
            message="修改失败！";
            request.setAttribute("message",message);
            request.getRequestDispatcher("/ad_changepass.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
