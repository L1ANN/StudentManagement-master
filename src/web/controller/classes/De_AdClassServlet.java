package web.controller.classes;

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
import java.util.ArrayList;
import java.util.List;

/**
 * @Author:L1ANN
 * @Description: 添加/删除班级人员的Servlet
 * @Date:Created in 13:10 2017/10/24
 * @Modified By:
 */
@WebServlet(name = "De_AdClassServlet")
public class De_AdClassServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ClassService classService = new ClassServiceImpl();
        //接收method和success参数，method用来判断删除/增加，success用来判断前端条件是否通过
        String method = request.getParameter("method");
        String success = request.getParameter("success");
        String[] stu_num = request.getParameterValues("stu_num");

        List<Integer> stunum = new ArrayList<>();
        if (stu_num != null) {
            for (String n : stu_num) {
                int num = -1;
                if (n != null && n != "") {
                    num = Integer.parseInt(n);
                    stunum.add(num);
                }
            }
        }
        int me = -2;
        int su = -1;
        String message = "";
        if (method != null && method != "") {
            me = Integer.parseInt(method);
        }
        if (success != null && success != "") {
            su = Integer.parseInt(success);
        }
        //如果me=-1，
        if (me == -1) {
            //如果me=-1&&su=1，执行删除动作且前端条件通过，执行删除动作
            if (su == 1) {
                boolean result = classService.deleteStudent(stunum.toArray());
                if (result) {
                    message = "删除成功！";
                    request.setAttribute("message", message);
                    request.getRequestDispatcher("/DetailClass.do").forward(request, response);
                    return;
                } else {
                    message = "删除失败，请重新选择要删除的人员！";
                    request.setAttribute("message", message);
                    request.getRequestDispatcher("/DetailClass.do").forward(request, response);
                    return;
                }
            }
            //前端条件未通过，删除失败
            else {
                message = "删除失败，请重新选择要删除的人员！";
                request.setAttribute("message", message);
                request.getRequestDispatcher("/DetailClass.do").forward(request, response);
                return;
            }
        }
        //如果me=1，执行添加动作
        if (me == 1) {
            //如果me=1&&su=1,执行添加动作且前端条件通过,执行添加动作
            if (su == 1) {
                request.getRequestDispatcher("/SelectNoClStudent.do").forward(request, response);
                return;
            }
            //前端条件未通过，删除失败
            else {
                message = "添加失败，宿舍人员已满！";
                request.setAttribute("message", message);
                request.getRequestDispatcher("/DetailClass.do").forward(request, response);
                return;
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
