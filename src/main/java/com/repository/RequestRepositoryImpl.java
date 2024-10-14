package com.repository;

import com.model.Request;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class RequestRepositoryImpl implements RequestRepository {

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myJPAUnit");

    @Override
    public void saveRequest(Request request) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();  // Begin transaction
            entityManager.persist(request);          // Persist the request object
            entityManager.getTransaction().commit(); // Commit the transaction
        } catch (Exception e) {
            if (entityManager != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback(); // Rollback in case of error
            }
            e.printStackTrace();
        } finally {
            if (entityManager != null) {
                entityManager.close();  // Ensure the EntityManager is closed
            }
        }
    }

    @Override
    public List<Request> findRequestsByAssignee(Long assigneeId) {
        EntityManager entityManager = null;
        List<Request> requests = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            requests = entityManager.createQuery("SELECT r FROM Request r WHERE r.assignee.id = :assigneeId", Request.class)
                    .setParameter("assigneeId", assigneeId)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (entityManager != null) {
                entityManager.close();  // Ensure the EntityManager is closed
            }
        }
        return requests;
    }

    public List<Request> findAllRequests() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Request> requests = entityManager.createQuery("SELECT r FROM Request r", Request.class).getResultList();
        entityManager.close();
        return requests;
    }
}
