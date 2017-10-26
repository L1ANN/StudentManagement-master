package web.UI;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author:L1ANN
 * @Description: 用于跳转到成绩列表的Servlet
 * @Date:Created in 15:32 2017/10/24
 * @Modified By:
 */
@WebServlet(name = "JumpRecordServlet")
public class JumpRecordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取系别加到请求属性，重定向
        request.setAttribute("count", 0);
        request.setAttribute("p", 0);
        request.getRequestDispatcher("/re_list.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
