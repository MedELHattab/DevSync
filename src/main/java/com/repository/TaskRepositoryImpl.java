package com.repository;

import com.model.Task;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class TaskRepositoryImpl implements TaskRepository {

    private EntityManager entityManager;

    public TaskRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Task createTask(Task task) {
        entityManager.getTransaction().begin();
        entityManager.persist(task);
        entityManager.getTransaction().commit();
        return task;
    }

    @Override
    public Task getTaskById(Long id) {
        return entityManager.find(Task.class, id);
    }

    @Override
    public List<Task> getAllTasks() {
        TypedQuery<Task> query = entityManager.createQuery("SELECT t FROM Task t", Task.class);
        return query.getResultList();
    }

    @Override
    public Task updateTask(Task task) {
        entityManager.getTransaction().begin();
        entityManager.merge(task);
        entityManager.getTransaction().commit();
        return task;
    }

    @Override
    public void deleteTask(Task task) {
        entityManager.getTransaction().begin();
        entityManager.remove(task);
        entityManager.getTransaction().commit();
    }
}
