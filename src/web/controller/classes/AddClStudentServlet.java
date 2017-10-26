package web.controller.classes;

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

/**
 * @Author:L1ANN
 * @Description: 添加班级人员的Servlet
 * @Date:Created in 14:18 2017/10/24
 * @Modified By:
 */
@WebServlet(name = "AddClStudentServlet")
public class AddClStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ClassService classService = new ClassServiceImpl();
        String stu_num = request.getParameter("stu_num");
        String cl_id = request.getParameter("cl_id");
        int num = -1;
        int id = -1;
        boolean result;
        String message = "";
        if (stu_num != null && stu_num != "") {
            num = Integer.parseInt(stu_num);
        }
        if (cl_id != null && cl_id != "") {
            id = Integer.parseInt(cl_id);
        }
        result = classService.addStudent(num, id);
        if (result) {
            message = "添加成功！";
            request.setAttribute("message", message);
            request.setAttribute("cl_id", cl_id);
            request.getRequestDispatcher("/DetailClass.do").forward(request, response);
            return;
        } else {
            message = "添加失败！";
            request.setAttribute("message", message);
            request.setAttribute("cl_id", cl_id);
            request.getRequestDispatcher("/DetailClass.do").forward(request, response);
            return;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
