package web.controller.classes;

import domain.Class;
import net.sf.json.JSONObject;
import service.ClassService;
import service.impl.ClassServiceImpl;

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
 * @Description: 根据专业id获取对应的班级列表的AJAX，用于成绩页面，条件查询
 * @Date:Created in 18:36 2017/10/24
 * @Modified By:
 */
@WebServlet(name = "SelectClassAJAXServlet")
public class SelectClassAJAXServlet extends HttpServlet {

    public static String CreateJsonString(String key, Object value) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(key, value);
        return jsonObject.toString();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ClassService classService = new ClassServiceImpl();
        List<Class> classes = new ArrayList<>();
        String ma_id = request.getParameter("majorId");
        int id = -1;
        if (ma_id != null && ma_id != "") {
            id = Integer.parseInt(ma_id);
        }
        classes = classService.selectClassByMajor(id);

        PrintWriter writer = response.getWriter();
        writer.print(CreateJsonString("classes", classes));
        writer.flush();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
