package web.controller.apartment;

import domain.Apartment;
import domain.Student;
import service.ApartmentService;
import service.impl.ApartmentServiceImpl;

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
 * @Description: 查看宿舍详情的Servlet
 * @Date:Created in 10:20 2017/10/23
 * @Modified By:
 */
@WebServlet(name = "DetailApartmentServlet")
public class DetailApartmentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ApartmentService apartmentService = new ApartmentServiceImpl();
        String ap_id = request.getParameter("ap_id");
        if (ap_id == null || ap_id == "") {
            ap_id = (String) request.getAttribute("ap_id");
        }
        Apartment apartment = new Apartment();
        List<Student> students = new ArrayList<Student>();
        int id = -1;
        if (ap_id != null && ap_id != "") {
            id = Integer.parseInt(ap_id);
        }
        apartment = apartmentService.selectApartmentById(id);
        students = apartmentService.selectStudentById(id);

        request.setAttribute("ap", apartment);
        request.setAttribute("students", students);

        request.getRequestDispatcher("/ap_detail.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
