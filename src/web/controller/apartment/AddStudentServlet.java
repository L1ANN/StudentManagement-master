package web.controller.apartment;

import service.ApartmentService;
import service.impl.ApartmentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author:L1ANN
 * @Description: 添加宿舍人员的Servlet
 * @Date:Created in 21:44 2017/10/23
 * @Modified By:
 */
@WebServlet(name = "AddStudentServlet")
public class AddStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ApartmentService apartmentService = new ApartmentServiceImpl();
        String stu_num = request.getParameter("stu_num");
        String ap_id = request.getParameter("ap_id");
        int num = -1;
        int id = -1;
        boolean result;
        String message = "";
        if (stu_num != null && stu_num != "") {
            num = Integer.parseInt(stu_num);
        }
        if (ap_id != null && ap_id != "") {
            id = Integer.parseInt(ap_id);
        }
        result = apartmentService.addStudent(num, id);
        if (result) {
            message = "添加成功！";
            request.setAttribute("message", message);
            request.setAttribute("ap_id", ap_id);
            request.getRequestDispatcher("/DetailApartment.do").forward(request, response);
            return;
        } else {
            message = "添加失败！";
            request.setAttribute("message", message);
            request.setAttribute("ap_id", ap_id);
            request.getRequestDispatcher("/DetailApartment.do").forward(request, response);
            return;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
