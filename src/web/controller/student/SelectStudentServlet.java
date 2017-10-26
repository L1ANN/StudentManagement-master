package web.controller.student;

import com.mysql.fabric.Server;
import domain.Student;
import service.StudentService;
import service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;


/**
 * @Author:L1ANN
 * @Description: 用于查询（全部查询/条件模糊查询）新生列表并提供分页的Servlet
 * @Date:Created in 13:35 2017/10/18
 * @Modified By:
 */
@WebServlet(name = "SelectStudentServlet")
public class SelectStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StudentService studentService = new StudentServiceImpl();
        //默认页数p是1，每页显示的记录数是2
        int p = 1;
        int record = 5;
        //jsp:接收要重定向的页面
        String jsp = request.getParameter("jsp");
        //p2：接收的页数
        String p2 = request.getParameter("p");
        //sqlname：条件模糊查询的字段名
        String sqlname = request.getParameter("sqlname");
        //sqlvalue：条件模糊查询的字段值
        String sqlvalue = request.getParameter("sqlvalue");
        //这个字段是宿舍详情页面，点击添加宿舍人员传过来的。
        String noApartment = request.getParameter("NoApartment");
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

        //将条件查询的条件保存为请求对象的属性
        request.setAttribute("sqlname", sqlname);
        request.setAttribute("sqlvalue", sqlvalue);

        //根据jsp的值跳转到不同的页面
        if ("list".equals(jsp)) {
            request.getRequestDispatcher("/stu_list.jsp").forward(request, response);
            return;
        }
        if ("majorlist".equals(jsp)) {

            request.getRequestDispatcher("/stu_majorlist.jsp").forward(request, response);
            return;
        }
        if ("classlist".equals(jsp)) {
            request.getRequestDispatcher("/stu_classlist.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
