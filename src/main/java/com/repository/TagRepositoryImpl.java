package com.repository;

import com.model.Tag;
import com.repository.TagRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class TagRepositoryImpl implements TagRepository {

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myJPAUnit");

    @Override
    public List<Tag> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<Tag> query = entityManager.createQuery("SELECT t FROM Tag t", Tag.class);
        List<Tag> tags = query.getResultList();
        entityManager.close(); // Close the entity manager
        return tags;
    }

    @Override
    public Tag findById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Tag tag = entityManager.find(Tag.class, id);
        entityManager.close(); // Close the entity manager
        return tag;
    }

    @Override
    public Tag save(Tag tag) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        if (tag.getId() == null) {
            entityManager.persist(tag);
        } else {
            tag = entityManager.merge(tag);
        }
        entityManager.getTransaction().commit();
        entityManager.close(); // Close the entity manager
        return tag;
    }

    @Override
    public void deleteById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            // Fetch the entity in the same transaction/session before removal
            Tag tag = entityManager.find(Tag.class, id);
            if (tag != null) {
                entityManager.remove(tag);  // Now the entity is managed and can be removed
            }

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();  // Rollback in case of an error
            }
            e.printStackTrace();
        } finally {
            entityManager.close();  // Always close the entity manager
        }
    }


    @Override
    public Tag findByName(String name) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<Tag> query = entityManager.createQuery("SELECT t FROM Tag t WHERE t.name = :name", Tag.class);
        query.setParameter("name", name);
        Tag tag = query.getSingleResult();
        entityManager.close(); // Close the entity manager
        return tag;
    }
}
