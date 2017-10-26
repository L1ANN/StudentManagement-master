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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author:L1ANN
 * @Description: 用于更新新生基本信息的Servlet
 * @Date:Created in 17:00 2017/10/18
 * @Modified By:
 */
@WebServlet(name = "UpdateStudentServlet")
public class UpdateStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Student stu = new Student();
        StudentService studentService = new StudentServiceImpl();
        String message;
        boolean result = false;
        String num = request.getParameter("stu_num");
        String stu_name = request.getParameter("stu_name");
        String age = request.getParameter("stu_age");
        String stu_gender = request.getParameter("stu_gender");
        String stu_ethnic = request.getParameter("stu_ethnic");
        String stu_native = request.getParameter("stu_native");
        String time = request.getParameter("stu_time");

        String birth = request.getParameter("stu_birth");
        String stu_phone = request.getParameter("stu_phone");
        String stu_address = request.getParameter("stu_address");

        int stu_num = 0, stu_age = 0, stu_time = 0;
        //将准考证号，年龄，入学时间转换成int
        if (num != null && num != "") {
            stu_num = Integer.parseInt(num);
        }
        if (age != null && age != "") {
            stu_age = Integer.parseInt(age);
        }
        if (time != null && time != "") {
            stu_time = Integer.parseInt(time);
        }
        //如果性别不是男或女
        if ((!stu_gender.equals("男")) && (!stu_gender.equals("女"))) {

            message = "性别必须是男或者女！请重新填写！";

            request.setAttribute("message", message);

            request.setAttribute("jsp", "detail");

            request.getRequestDispatcher("/DetailStudent.do").forward(request, response);
            return;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date stu_birth = new java.sql.Date(date.getTime());

        stu.setStu_num(stu_num);
        stu.setStu_name(stu_name);
        stu.setStu_age(stu_age);
        stu.setStu_gender(stu_gender);
        stu.setStu_ethnic(stu_ethnic);
        stu.setStu_native(stu_native);
        stu.setStu_time(stu_time);
        stu.setStu_birth(stu_birth);
        stu.setStu_phone(stu_phone);
        stu.setStu_address(stu_address);

        result = studentService.updateStudent(stu);
        if (result == true) {
            message = "记录修改成功!";

            request.setAttribute("message", message);

            request.setAttribute("jsp", "detail");

            request.getRequestDispatcher("/DetailStudent.do").forward(request, response);
        } else {
            message = "记录修改失败";

            request.setAttribute("message", message);

            request.setAttribute("jsp", "detail");

            request.getRequestDispatcher("/DetailStudent.do").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
