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
 * @Description: 根据准考证号删除新生记录的servlet
 * @Date:Created in 0:51 2017/10/19
 * @Modified By:
 */
@WebServlet(name = "DeleteStudentServlet")
public class DeleteStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String num = request.getParameter("stu_num");
        String message = "";
        int stu_num = -1;
        boolean result = false;
        StudentService studentService = new StudentServiceImpl();

        if (num != "" && num != null) {
            stu_num = Integer.parseInt(num);
        }

        result = studentService.deleteStudent(stu_num);
        if (result == true) {
            message = "删除成功！";
            request.setAttribute("message", message);
            request.getRequestDispatcher("/SelectStudent.do").forward(request, response);
        } else {
            message = "删除失败！";
            request.setAttribute("message", message);
            request.getRequestDispatcher("/SelectStudent.do").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
