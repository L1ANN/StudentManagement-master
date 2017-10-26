package web.controller.classes;

import domain.Apartment;
import domain.Class;
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
 * @Description: 查看班级详情的Servlet
 * @Date:Created in 11:27 2017/10/24
 * @Modified By:
 */
@WebServlet(name = "DetailClassServlet")
public class DetailClassServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ClassService classService = new ClassServiceImpl();
        String cl_id = request.getParameter("cl_id");
        if (cl_id == null || cl_id == "") {
            cl_id = (String) request.getAttribute("cl_id");
        }
        Class classes = new Class();
        List<Student> students = new ArrayList<Student>();
        int id = -1;
        if (cl_id != null && cl_id != "") {
            id = Integer.parseInt(cl_id);
        }
        classes = classService.selectClassById(id);
        students = classService.selectStudentById(id);

        request.setAttribute("cl", classes);
        request.setAttribute("students", students);

        request.getRequestDispatcher("/cl_detail.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
