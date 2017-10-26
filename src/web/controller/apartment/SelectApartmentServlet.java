package web.controller.apartment;

import domain.Apartment;
import service.ApartmentService;
import service.impl.ApartmentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author:L1ANN
 * @Description: 获取所有宿舍列表/模糊查找的宿舍列表，并提供分页功能的Servlet
 * @Date:Created in 14:36 2017/10/22
 * @Modified By:
 */
@WebServlet(name = "SelectApartmentServlet")
public class SelectApartmentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ApartmentService apartmentService = new ApartmentServiceImpl();
        //设定默认的页面数page和每页显示的记录数record
        int page = 1;
        int record = 5;
        //接收request参数，页面数p，条件查询名称sqlname，条件查询值sqlvalue
        String p2 = request.getParameter("p");
        String sqlname = request.getParameter("sqlname");
        String sqlvalue = request.getParameter("sqlvalue");
        //调用ApartmentService服务获取总页数
        int count = apartmentService.getCount(sqlname, sqlvalue, record);
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
        List<Apartment> apartments = apartmentService.apartmentPage(sqlname, sqlvalue, page, record);
        request.setAttribute("Apartment", apartments);
        request.setAttribute("p", page);
        request.setAttribute("count", count);
        //将条件查询的条件设为请求的属性
        request.setAttribute("sqlname", sqlname);
        request.setAttribute("sqlvalue", sqlvalue);
        //重定向
        request.getRequestDispatcher("/ap_list.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
