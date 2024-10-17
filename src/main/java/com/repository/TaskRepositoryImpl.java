package com.repository;

import com.model.Tag;
import com.model.Task;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.List;

public class TaskRepositoryImpl implements TaskRepository {

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myJPAUnit");

    @Override
    public Task getTaskById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Task task = null;
        try {
            task = entityManager.find(Task.class, id);

            // Manually initialize the tags collection to avoid LazyInitializationException
            if (task != null) {
                task.getTags().size();  // This will force the tags to be loaded
            }
        } finally {
            entityManager.close();
        }
        return task;
    }


    @Override
    public List<Task> getAllTasks() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<Task> query = entityManager.createQuery("SELECT t FROM Task t", Task.class);
        List<Task> tasks = query.getResultList();

        // Initialize lazy collections (tags) for each task
        for (Task task : tasks) {
            // Explicitly initialize the tags collection
            task.getTags().size();  // Access the collection to force initialization
        }

        entityManager.close(); // Close the entity manager
        return tasks;
    }


    @Override
    public void createTask(Task task, List<Tag> tags) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            // Persist the task first to get the generated ID
            entityManager.persist(task);

            // If there are tags, associate them with the task
            if (tags != null) {
                for (Tag tag : tags) {
                    tag = entityManager.find(Tag.class, tag.getId()); // Retrieve the tag entity
                    if (tag != null) {
                        task.addTag(tag); // Add the tag to the task
                    }
                }
            }

            // Merge the task to update with tags
            entityManager.merge(task);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e; // Rethrow or handle the exception
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void updateTask(Task task, List<Tag> tags) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            // Update the task
            entityManager.merge(task);

            // Clear existing tags (if any)
            task.getTags().clear();

            // Associate new tags
            if (tags != null) {
                for (Tag tag : tags) {
                    tag = entityManager.find(Tag.class, tag.getId()); // Retrieve the tag entity
                    if (tag != null) {
                        task.addTag(tag); // Add the tag to the task
                    }
                }
            }

            // Update the task again to save the tags
            entityManager.merge(task);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e; // Rethrow or handle the exception
        } finally {
            entityManager.close();
        }
    }


    @Override
    public void deleteTask(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Task task = entityManager.find(Task.class, id);
            if (task != null) {
                entityManager.remove(task);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e; // Rethrow or handle exception
        } finally {
            entityManager.close();
        }
    }

    public void updateAssignee(Task task) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            // Update the task
            entityManager.merge(task);

            // Update the task again to save the tags
            entityManager.merge(task);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e; // Rethrow or handle the exception
        } finally {
            entityManager.close();
        }
    }

    public void updateOverdueTasks() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            // Update tasks where due_date is past today and status is not 'Completed'
            entityManager.createQuery(
                            "UPDATE Task t SET t.status = 'Overdue' WHERE t.dueDate < :today AND t.status != 'Completed'")
                    .setParameter("today", LocalDate.now())
                    .executeUpdate();

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();  // Proper logging should be used here
        } finally {
            entityManager.close();
        }
    }
}
