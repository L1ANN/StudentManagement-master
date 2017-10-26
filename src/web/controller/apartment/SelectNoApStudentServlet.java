package web.controller.apartment;

import domain.Student;
import service.ApartmentService;
import service.impl.ApartmentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author:L1ANN
 * @Description: 获取没有宿舍人员列表的Servlet
 * @Date:Created in 21:05 2017/10/23
 * @Modified By:
 */
@WebServlet(name = "SelectNoApStudentServlet")
public class SelectNoApStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ApartmentService apartmentService = new ApartmentServiceImpl();
        List<Student> students = new ArrayList<>();
        int p = 1;
        int record = 5;
        String p2 = request.getParameter("p");
        String ap_id = request.getParameter("ap_id");
        int count = apartmentService.getCount(record);

        if (p2 != null && p2 != "") {
            p = Integer.parseInt(p2);
        }
        if (p <= 0) {
            p = 1;
        }
        if (p >= count) {
            p = count;
        }
        students = apartmentService.noApartmentPage(p, record);
        request.setAttribute("students", students);
        request.setAttribute("p", p);
        request.setAttribute("count", count);
        request.setAttribute("ap_id", ap_id);
        request.getRequestDispatcher("/ap_noaplist.jsp").forward(request, response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
