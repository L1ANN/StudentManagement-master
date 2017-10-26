package web.controller.major;

import domain.Department;
import domain.Major;
import domain.Student;
import net.sf.json.JSONObject;
import service.DepartmentService;
import service.MajorService;
import service.impl.DepartmentServiceImpl;
import service.impl.MajorServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author:L1ANN
 * @Description: 根据系别获取系别下专业的Servlet，用于成绩查询页面以及新生专业调整页面
 * @Date:Created in 16:35 2017/10/19
 * @Modified By:
 */

@WebServlet(name = "SelectMajorAJAXServlet")
public class SelectMajorAJAXServlet extends HttpServlet {
    //将接收的value参数转换成JSON格式，其中key也加入到JSON中作为JSON字符串的名称
    public String createJsonString(String key, Object value) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(key, value);
        return jsonObject.toString();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        MajorService majorService = new MajorServiceImpl();
        List<Major> majors = new ArrayList<>();
        int num = -1;
        //1、接收选中的系别的id
        String departmentId = request.getParameter("departmentId");
        //2、获取所选中系别下的专业保存到majors
        if (departmentId != null && departmentId != "") {
            int id = Integer.parseInt(departmentId);
            majors = majorService.selectMajorByDepartment(id);
        }
        //3、创建响应字符输出流，将List<Major> majors数据转换成JSON格式，并通过输出流返回给AJAX
        PrintWriter writer = response.getWriter();
        writer.print(createJsonString("majors", majors));
        writer.flush();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
