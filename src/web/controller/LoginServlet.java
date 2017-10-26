package web.controller;

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
 * @Description: 用于处理用户登录的Servlet
 * @Date:Created in 18:19 2017/10/17
 * @Modified By:
 */
@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取用户填写的用户名
        String name = request.getParameter("name");
        //获取用户填写的密码
        int password = Integer.parseInt(request.getParameter("password"));
        AdminService service = new AdminServiceImpl();
        //用户登录
        Admin admin = service.login(name, password);
        //登录失败
        if (admin == null) {
            String message = "对不起，用户名或密码有误!请重新登录!2s后为您自动跳到登录页面!!<meta http-equiv='refresh' content='2;url=Login'/>";
            request.setAttribute("message", message);
            request.getRequestDispatcher("/message.jsp").forward(request, response);
            return;
        }
        //登录成功
        request.getSession().setAttribute("admin", admin);
        String message = String.format("恭喜：%s登录成功!本页将在3s后跳转到首页!!<meta http-equiv='refresh' content='3;url=%s'/>", admin.getAd_name(), request.getContextPath() + "/index.jsp");
        request.setAttribute("message", message);
        request.getRequestDispatcher("/message.jsp").forward(request, response);


    }
}
