package web.task;

import model.entity.task.Task;
import org.json.JSONObject;
import service.ServiceFactory;
import service.task.TaskService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Date: 10.05.13
 *
 * @author Андрей
 */
public class TaskServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TaskService taskService = ServiceFactory.getTaskService();
        PrintWriter printWriter = response.getWriter();
        if (request.getParameter("task_id") != null) {
            long taskId = Long.parseLong(request.getParameter("task_id"));
            Task task = taskService.getTaskById(taskId);
            JSONObject jsonObject = new JSONObject(task);
            printWriter.println(jsonObject);
        } else {
            List<Task> taskList = taskService.getAllTasks();
            for (Task task : taskList) {
                JSONObject jsonObject = new JSONObject(task);
                printWriter.println(jsonObject);
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
