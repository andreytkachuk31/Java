package web.task;

import service.ServiceFactory;
import service.task.TaskService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Date: 10.05.13
 *
 * @author Андрей
 */
public class TaskDeleteServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TaskService taskService = ServiceFactory.getTaskService();
        PrintWriter printWriter = response.getWriter();
        if (request.getParameter("task_id") != null) {
            try {
                String parameterTaskId = request.getParameter("task_id");
                String taskIds[] = parameterTaskId.split(",");
                for (String taskId : taskIds) {
                    taskService.deleteTaskById(Long.parseLong(taskId));
                }
                printWriter.println("successv");
            } catch (Exception e) {
                printWriter.println("error");
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
