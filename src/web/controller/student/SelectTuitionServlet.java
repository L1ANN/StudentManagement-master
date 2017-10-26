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
import java.util.List;

/**
 * @Author:L1ANN
 * @Description: 用户查询新生缴费列表并提供分页的Servlet
 * @Date:Created in 12:40 2017/10/19
 * @Modified By:
 */
@WebServlet(name = "SelectTuitionServlet")
public class SelectTuitionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Student stu = new Student();
        StudentService studentService = new StudentServiceImpl();
        String sqlname = "";
        String sqlvalue = "";
        //默认页数p是1，每页显示的记录数是2
        int p = 1;
        int record = 5;

        //p2：接收的页数
        String p2 = request.getParameter("p");
        //sql：接收缴费的条件查询  1（已缴费）/0（未缴费）
        String sql = request.getParameter("sqlname");
        //根据缴费情况，为sqlname和sqlvalue分别赋值，默认情况都为""
        if (sql != null && sql != "") {
            if (sql.equals("1")) {
                sqlname = "stu_tuition";
                sqlvalue = "22368";
            }
            if (sql.equals("0")) {
                sqlname = "stu_tuition";
                sqlvalue = "0";
            }
        }


        //获取总页数
        int count = studentService.getCount(sqlname, sqlvalue, record);

        //根据不同的情况，为默认p赋值
        //①如果p2不为null且不为空，将p2值赋给p
        if (p2 != null && p2 != "") {
            p = Integer.parseInt(p2);
        }
        //②如果页数p<=0，那么赋值为首页
        if (p <= 0) {
            p = 1;
        }
        //③如果页数p>=总页数count，那么赋值为尾页
        if (p >= count) {
            p = count;
        }

        //将请求得到的数据保存为请求对象的属性，分别是新生对象，当前请求页数，总页数
        List<Student> students = studentService.studentPage(sqlname, sqlvalue, p, record);
        request.setAttribute("Student", students);
        request.setAttribute("p", p);
        request.setAttribute("count", count);


        //将缴费情况保存到请求的属性中，用于保存下拉框的状态以及搜索的情况
        request.setAttribute("name", sql);


        //将请求转发到student.jsp
        request.getRequestDispatcher("/stu_tuition.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
