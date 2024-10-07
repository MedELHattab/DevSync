package com.repository;

import com.model.UserTokens;
import jakarta.persistence.EntityManager;

public class TokenRepositoryImpl implements TokenRepository {

    private EntityManager entityManager;

    public TokenRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public UserTokens findTokensByUser(Long userId) {
        return entityManager.find(UserTokens.class, userId);
    }

    @Override
    public void updateUserTokens(UserTokens tokens) {
        entityManager.getTransaction().begin();
        entityManager.merge(tokens);
        entityManager.getTransaction().commit();
    }
}
