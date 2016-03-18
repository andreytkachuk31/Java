package dao.task.impl;

import dao.PersistanceGenericDAO;
import dao.task.TaskDAO;
import model.entity.task.Task;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Andrii_Tkachuk
 * @since 1/8/2015
 */
public class TaskDAOImpl extends PersistanceGenericDAO implements TaskDAO {

    @Override
    public void addTask(Task task) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(task);
            entityManager.getTransaction().commit();
        } finally {
            closeEntityManager(entityManager);
        }
    }

    @Override
    public void updateTask(Task task) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(task);
            entityManager.getTransaction().commit();
        } finally {
            closeEntityManager(entityManager);
        }
    }

    @Override
    public Task getTaskById(long id) {
        EntityManager entityManager = getEntityManager();
        Task task = null;
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("SELECT u FROM Task u WHERE u.id = :id");
            query.setParameter("id", id);
            task = (Task) query.getSingleResult();
            entityManager.getTransaction().commit();
        } finally {
            closeEntityManager(entityManager);
        }
        return task;
    }

    @Override
    public List<Task> getAllTasks() {
        EntityManager entityManager = getEntityManager();
        List<Task> taskList;
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("SELECT u FROM Task u");
            taskList = query.getResultList();
            entityManager.getTransaction().commit();
        } finally {
            closeEntityManager(entityManager);
        }
        return taskList;
    }

    @Override
    public void deleteTaskById(long id) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            Task task = entityManager.find(Task.class, id);
            entityManager.remove(task);
            entityManager.getTransaction().commit();
        } finally {
            closeEntityManager(entityManager);
        }
    }

    @Override
    public void deleteAllTasks() {
        EntityManager entityManager = getEntityManager();
        try {
            for (int i = 38; i <= 38; i++) {
                entityManager.getTransaction().begin();
                Task task = entityManager.find(Task.class, i);
                entityManager.remove(task);
                entityManager.getTransaction().commit();
            }
        } finally {
            closeEntityManager(entityManager);
        }
    }
}
