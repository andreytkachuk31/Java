package dao.task;

import model.entity.task.Task;

import java.util.List;

/**
 * @author Andrii_Tkachuk
 * @since 1/8/2015
 */
public interface TaskDAO {

    void addTask(Task task);

    void updateTask(Task task);

    Task getTaskById(long id);

    List<Task> getAllTasks();

    void deleteTaskById(long id);

    void deleteAllTasks();
}
