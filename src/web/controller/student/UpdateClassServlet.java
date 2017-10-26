package web.controller.student;

import service.StudentService;
import service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author:L1ANN
 * @Description: 用于更新新生班级信息的Servlet
 * @Date:Created in 11:57 2017/10/20
 * @Modified By:
 */
@WebServlet(name = "UpdateClassServlet")
public class UpdateClassServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = "";
        boolean result = false;
        int num = -1;
        int id = -1;
        StudentService studentService = new StudentServiceImpl();
        String stu_num = request.getParameter("stu_num");
        String cl_id = request.getParameter("cl_id");
        if (stu_num != null && stu_num != "") {
            num = Integer.parseInt(stu_num);
        }
        if (cl_id != null && cl_id != "") {
            id = Integer.parseInt(cl_id);
        }
        if (num == -1 || id == -1) {
            result = false;
        } else {
            result = studentService.updateClass(num, id);
        }
        if (result) {
            message = "班级修改成功!";

            request.setAttribute("message", message);

            request.setAttribute("jsp", "classdetail");

            request.getRequestDispatcher("/DetailStudent.do").forward(request, response);
        } else {
            message = "班级修改失败";

            request.setAttribute("message", message);

            request.setAttribute("jsp", "classdetail");

            request.getRequestDispatcher("/DetailStudent.do?jsp=classdetail").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
