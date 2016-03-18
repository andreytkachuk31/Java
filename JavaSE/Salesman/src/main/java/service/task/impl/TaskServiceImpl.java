package service.task.impl;

import com.google.gson.Gson;
import dao.task.TaskDAO;
import dao.task.impl.TaskDAOImpl;
import model.entity.task.Status;
import model.entity.task.Task;
import model.matrixdistance.MatrixDistance;
import model.matrixdistance.Point;
import model.method.BruteForce;
import org.json.JSONException;
import service.task.TaskService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Andrii_Tkachuk
 * @since 1/8/2015
 */
public class TaskServiceImpl implements TaskService {

    private static long countTask  = 0L;

    private TaskDAO taskDAO;

    public TaskServiceImpl() {
        taskDAO = new TaskDAOImpl();
    }

    @Override
    public void addTaskCalculateBestPath(List<Point> points) {
        Task task = new Task();
        try {
            task.setName("Task" + countTask);
            task.setPoints(new Gson().toJson(points));
            task.setStatus(Status.IN_PROGRESS);
            taskDAO.addTask(task);
            Map<Integer, Integer> bestPath = new BruteForce().calculateBestPathByBruteForce(MatrixDistance.buildMatrixDistance(points));
            task.setStatus(Status.SUCCESS);
            task.setBestPath(new Gson().toJson(bestPath));
            taskDAO.updateTask(task);
        } catch (JSONException | IOException | InterruptedException e) {
            task.setStatus(Status.BUG);
            taskDAO.updateTask(task);
            System.err.println(e);
        } finally {
            countTask++;
        }
    }

    @Override
    public Task getTaskById(long id) {
        return taskDAO.getTaskById(id);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskDAO.getAllTasks();
    }

    @Override
    public void deleteTaskById(long id) {
        taskDAO.deleteTaskById(id);
    }
}
