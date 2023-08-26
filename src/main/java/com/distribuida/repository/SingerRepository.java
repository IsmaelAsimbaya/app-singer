package com.distribuida.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

import com.distribuida.db.Singer;

@ApplicationScoped
public class SingerRepository {
    @PersistenceContext
    private EntityManager em;

    public List<Singer> getAllSingers() {
        return em.createQuery("SELECT s FROM Singer s", Singer.class)
                .getResultList();
    }

    public Singer getSingerById(Integer id) {
        return em.find(Singer.class, id);
    }

    public Singer createSinger(Singer singer) {
        em.persist(singer);
        return singer;
    }

    public Singer updateSinger(Singer singer) {
        em.merge(singer);
        return singer;
    }

    public void deleteSinger(Integer id) {
        Singer singer = em.find(Singer.class, id);
        if (singer != null) {
            em.remove(singer);
        }
    }
}
