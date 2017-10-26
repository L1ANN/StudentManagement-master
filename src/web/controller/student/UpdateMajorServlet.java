package web.controller.student;

import domain.Student;
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
 * @Description: 用于更新新生专业信息的Servlet
 * @Date:Created in 13:58 2017/10/19
 * @Modified By:
 */
@WebServlet(name = "UpdateMajorServlet")
public class UpdateMajorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int num = -1;
        int deid = -1;
        int maid = -1;
        boolean result = false;
        String message = "";
        //1、获取准考证号，系别id，专业id，来更新数据库
        StudentService studentService = new StudentServiceImpl();
        Student student = new Student();
        String stu_num = request.getParameter("stu_num");
        String de_id = request.getParameter("de_id");
        String ma_id = request.getParameter("ma_id");

        if (stu_num != null && stu_num != "") {
            num = Integer.parseInt(stu_num);
        }
        if (de_id != null && de_id != "") {
            deid = Integer.parseInt(de_id);
        }
        if (ma_id != null && ma_id != "") {
            maid = Integer.parseInt(ma_id);
        }
        if (num == -1 || deid == -1 || maid == -1) {
            result = false;

        } else {
            result = studentService.updateMajor(num, deid, maid);
        }

        //2、根据result的值判断是否更新成功
        if (result) {
            message = "专业修改成功!";

            request.setAttribute("message", message);

            request.setAttribute("jsp", "majordetail");

            request.getRequestDispatcher("/DetailStudent.do").forward(request, response);
        } else {
            message = "专业修改失败";

            request.setAttribute("message", message);

            request.setAttribute("jsp", "majordetail");

            request.getRequestDispatcher("/DetailStudent.do").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
