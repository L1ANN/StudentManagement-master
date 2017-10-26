package web.UI;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author:L1ANN
 * @Description: LoginUIServlet负责为用户输出登录页面，当用户访问LoginUIServlet时，就跳转到WEB-INF/pages目录下的login.jsp
 * @Date:Created in 18:04 2017/10/17
 * @Modified By:
 */
public class LoginUIServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }
}
