package web.controller.classes;

import domain.Student;
import service.ApartmentService;
import service.ClassService;
import service.impl.ApartmentServiceImpl;
import service.impl.ClassServiceImpl;

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
 * @Description: 查询没有班级的新生列表的Servlet
 * @Date:Created in 13:41 2017/10/24
 * @Modified By:
 */
@WebServlet(name = "SelectNoClStudentServlet")
public class SelectNoClStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ClassService classService = new ClassServiceImpl();
        List<Student> students = new ArrayList<>();
        int p = 1;
        int record = 5;
        String p2 = request.getParameter("p");
        String cl_id = request.getParameter("cl_id");
        int count = classService.getCount(record);

        if (p2 != null && p2 != "") {
            p = Integer.parseInt(p2);
        }
        if (p <= 0) {
            p = 1;
        }
        if (p >= count) {
            p = count;
        }
        students = classService.noClassPage(p, record);
        request.setAttribute("students", students);
        request.setAttribute("p", p);
        request.setAttribute("count", count);
        request.setAttribute("cl_id", cl_id);
        request.getRequestDispatcher("/cl_nocllist.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
