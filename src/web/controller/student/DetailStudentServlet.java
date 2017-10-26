package web.controller.student;

import domain.Class;
import domain.Department;
import domain.Student;
import service.ClassService;
import service.DepartmentService;
import service.StudentService;
import service.impl.ClassServiceImpl;
import service.impl.DepartmentServiceImpl;
import service.impl.StudentServiceImpl;

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
 * @Description: 根据准考证号获取新生信息的Servlet
 * @Date:Created in 17:14 2017/10/18
 * @Modified By:
 */

public class DetailStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String num = request.getParameter("stu_num");
        String jsp = request.getParameter("jsp");
        if (jsp == null || jsp == "") {
            jsp = (String) request.getAttribute("jsp");
        }
        int number = 0;
        //1、根据num获取新生对象保存到student，并添加到请求属性当中
        StudentService studentService = new StudentServiceImpl();
        Student student = new Student();
        if (num != null && num != "") {
            number = Integer.parseInt(num);
            student = studentService.selectStudentByNum(number);
        }
        request.setAttribute("stu", student);
        //2、根据jsp的值跳转到不同的页面

        //跳到新生详情页面
        if ("detail".equals(jsp)) {
            request.getRequestDispatcher("/stu_detail.jsp").forward(request, response);
            return;
        }
        //跳到新生专业详情页面
        if ("majordetail".equals(jsp)) {
            //获取系别列表，并保存到请求属性中
            DepartmentService departmentService = new DepartmentServiceImpl();
            List<Department> departments = new ArrayList<Department>();
            departments = departmentService.selectAllDepartment();
            request.setAttribute("departmentlist", departments);
            //重定向到新生专业详情页面
            request.getRequestDispatcher("/stu_majordetail.jsp").forward(request, response);
            return;
        }
        //跳到新生班级详情页面
        if ("classdetail".equals(jsp)) {
            //获取新生专业id
            int ma_id = studentService.getMajorByNum(number);
            //获取班级列表，并保存到请求属性中
            ClassService classService = new ClassServiceImpl();
            List<Class> classes = new ArrayList<>();
            classes = classService.selectClassByMajor(ma_id);
            request.setAttribute("classes", classes);
            //重定向到新生班级详情页面
            request.getRequestDispatcher("/stu_classdetail.jsp").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
