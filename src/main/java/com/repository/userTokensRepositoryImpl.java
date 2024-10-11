package com.repository;

import com.model.UserTokens;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class userTokensRepositoryImpl implements userTokensRepository {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("myJPAUnit");

    @Override
    public void createUserTokens(UserTokens userTokens) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(userTokens);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Method to reset daily tokens without raw SQL
    public void resetDailyTokens() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            // Fetch all UserTokens entities
            List<UserTokens> userTokensList = em.createQuery("SELECT ut FROM UserTokens ut", UserTokens.class).getResultList();
            // Reset daily tokens for each UserTokens entity
            for (UserTokens userTokens : userTokensList) {
                userTokens.setDailyTokens(2);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Method to reset monthly tokens without raw SQL
    public void resetMonthlyTokens() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            // Fetch all UserTokens entities
            List<UserTokens> userTokensList = em.createQuery("SELECT ut FROM UserTokens ut", UserTokens.class).getResultList();
            // Reset monthly tokens for each UserTokens entity
            for (UserTokens userTokens : userTokensList) {
                userTokens.setMonthlyTokens(1);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }


    public UserTokens findByUserId(Long userId) {
        EntityManager em = emf.createEntityManager();
        try {
            // Assuming 'User' entity is mapped to 'id' as the primary key
            return em.createQuery("SELECT ut FROM UserTokens ut WHERE ut.user.id = :userId", UserTokens.class)
                    .setParameter("userId", userId)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }


    public void setMonthlyTokensToZero() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            // Fetch all UserTokens entities
            List<UserTokens> userTokensList = em.createQuery("SELECT ut FROM UserTokens ut", UserTokens.class).getResultList();
            // Set monthly tokens to 0 for each UserTokens entity
            for (UserTokens userTokens : userTokensList) {
                userTokens.setMonthlyTokens(0);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
