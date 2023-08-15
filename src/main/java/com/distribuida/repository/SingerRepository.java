package com.distribuida.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

import com.distribuida.db.Singer;

@ApplicationScoped
public class SingerRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Singer> getAllSingers() {
        return entityManager.createQuery("SELECT s FROM Singer s", Singer.class)
                .getResultList();
    }

    public Singer getSingerById(Integer id) {
        return entityManager.find(Singer.class, id);
    }

    public void createSinger(Singer singer) {
        entityManager.persist(singer);
    }

    public void updateSinger(Singer singer) {
        entityManager.merge(singer);
    }

    public void deleteSinger(Integer id) {
        Singer singer = entityManager.find(Singer.class, id);
        if (singer != null) {
            entityManager.remove(singer);
        }
    }
}
