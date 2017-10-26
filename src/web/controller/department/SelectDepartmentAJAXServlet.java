package web.controller.department;

import dao.DepartmentDAO;
import dao.impl.DepartmentDAOImpl;
import domain.Department;
import net.sf.json.JSONObject;

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
 * @Description: 获取所有系别的AJAX，用于在成绩页面提供系别
 * @Date:Created in 17:55 2017/10/24
 * @Modified By:
 */
@WebServlet(name = "SelectDepartmenAJAXtServlet")
public class SelectDepartmentAJAXServlet extends HttpServlet {

    public static String CreateJsonString(String key, Object value) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(key, value);
        return jsonObject.toString();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DepartmentDAO departmentDAO = new DepartmentDAOImpl();
        List<Department> departments = new ArrayList<Department>();
        departments = departmentDAO.slectAllDepartment();

        PrintWriter writer = response.getWriter();
        writer.print(CreateJsonString("departments", departments));
        writer.flush();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
