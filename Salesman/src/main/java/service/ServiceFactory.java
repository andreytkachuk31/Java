package service;

import service.task.TaskService;
import service.task.impl.TaskServiceImpl;

/**
 * @author Andrii_Tkachuk
 * @since 1/8/2015
 */
public final class ServiceFactory {

    public static TaskService getTaskService() {
        return new TaskServiceImpl();
    }
}
