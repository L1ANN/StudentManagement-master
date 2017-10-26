package web.controller.record;

import domain.Student;
import service.RecordService;
import service.StudentService;
import service.impl.RecordServiceImpl;
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
 * @Description: 获取成绩列表/模糊查询成绩列表并提供分页的Servlet
 * @Date:Created in 20:06 2017/10/24
 * @Modified By:
 */
@WebServlet(name = "SelectRecordServlet")
public class SelectRecordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RecordService recordService = new RecordServiceImpl();
        //默认页数p是1，每页显示的记录数是2
        int p = 1;
        int record = 5;

        //p2：接收的页数
        String p2 = request.getParameter("p");
        //ma_id：要查询的专业id
        String ma_id = request.getParameter("ma_id");
        //cl_id：要查询的班级id
        String cl_id = request.getParameter("cl_id");
        int maid = -1;
        int clid = -1;
        if (ma_id != null && ma_id != "") {
            maid = Integer.parseInt(ma_id);
        }
        if (cl_id != null && cl_id != "") {
            clid = Integer.parseInt(cl_id);
        }
        //获取总页数
        int count = recordService.getCount(maid, clid, record);

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
        List<Student> students = recordService.recordPage(maid, clid, p, record);
        request.setAttribute("Student", students);
        request.setAttribute("p", p);
        request.setAttribute("count", count);

        //将条件查询的条件保存为请求对象的属性
        request.setAttribute("ma_id", ma_id);
        request.setAttribute("cl_id", cl_id);

        //根据jsp的值跳转到不同的页面
        request.getRequestDispatcher("/re_list.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
