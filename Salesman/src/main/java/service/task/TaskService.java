package service.task;

import model.entity.task.Task;
import model.matrixdistance.Point;

import java.util.List;

/**
 * @author Andrii_Tkachuk
 * @since 1/8/2015
 */
public interface TaskService {

    void addTaskCalculateBestPath(List<Point> points);

    Task getTaskById(long id);

    List<Task> getAllTasks();

    void deleteTaskById(long id);
}
