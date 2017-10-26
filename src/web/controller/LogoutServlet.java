package web.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author:L1ANN
 * @Description: 注销功能
 * @Date:Created in 19:43 2017/10/17
 * @Modified By:
 */
@WebServlet(name = "LogoutServlet")
public class LogoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //移除存储在session中的admin对象，实现注销功能
        request.getSession().removeAttribute("admin");
        String message = "注销成功!!3秒后为您自动跳到登录页面!!<meta http-equiv='refresh' content='3;url=Login'/>";
        request.setAttribute("message", message);
        request.getRequestDispatcher("/message.jsp").forward(request, response);

    }
}
