package web.controller.classes;

import domain.Apartment;
import domain.Class;
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
import java.util.List;

/**
 * @Author:L1ANN
 * @Description: 获取全部班级列表/模糊查询班级列表，并提供分页的Servlet
 * @Date:Created in 10:24 2017/10/24
 * @Modified By:
 */
@WebServlet(name = "SelectClassServlet")
public class SelectClassServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ClassService classService = new ClassServiceImpl();
        //设定默认的页面数page和每页显示的记录数record
        int page = 1;
        int record = 5;
        //接收request参数，页面数p，条件查询名称sqlname，条件查询值sqlvalue
        String p2 = request.getParameter("p");
        String sqlname = request.getParameter("sqlname");
        String sqlvalue = request.getParameter("sqlvalue");
        //调用classService服务获取总页数
        int count = classService.getCount(sqlname, sqlvalue, record);
        //根据不同情况，为默认page赋值
        //①p2非空，将p值设为p2
        if (p2 != null && p2 != "") {
            page = Integer.parseInt(p2);
        }
        //②如果页面page<=0，将page设为首页
        if (page <= 0) {
            page = 1;
        }
        //③如果页面page>=count,将page设为尾页
        if (page >= count) {
            page = count;
        }
        //将请求得到的数据保存为请求对象的属性，分别是宿舍集合，当前请求页数，总页数
        List<Class> classes = classService.classPage(sqlname, sqlvalue, page, record);
        request.setAttribute("classes", classes);
        request.setAttribute("p", page);
        request.setAttribute("count", count);
        //将条件查询的条件设为请求的属性
        request.setAttribute("sqlname", sqlname);
        request.setAttribute("sqlvalue", sqlvalue);
        //重定向
        request.getRequestDispatcher("/cl_list.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
