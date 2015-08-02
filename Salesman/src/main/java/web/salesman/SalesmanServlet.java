package web.salesman;

import com.google.gson.Gson;
import model.matrixdistance.Point;
import org.apache.commons.lang3.StringUtils;
import service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

/**
 * Date: 10.05.13
 *
 * @author Андрей
 */
public class SalesmanServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        String address = request.getParameter("address");
        if (StringUtils.isBlank(address)) {
            writer.print("Not address!");
        } else {
            List<Point> points = Arrays.asList(new Gson().fromJson(address, Point[].class));
            ServiceFactory.getTaskService().addTaskCalculateBestPath(points);
            writer.print(request.getParameter("callback") + "({\"value\":\"");
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
