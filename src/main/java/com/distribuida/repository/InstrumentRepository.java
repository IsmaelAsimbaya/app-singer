package com.distribuida.repository;

import com.distribuida.db.Instrument;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@ApplicationScoped
public class InstrumentRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Instrument> getAllInstruments() {
        return em.createQuery("SELECT i FROM Instrument i", Instrument.class)
                .getResultList();
    }

    public Instrument getInstrumentById(Integer id) {
        return em.find(Instrument.class, id);
    }

    public Instrument createInstrument(Instrument instrument) {
        em.persist(instrument);
        return instrument;
    }

    public Instrument updateInstrument(Instrument instrument) {
        em.merge(instrument);
        return instrument;
    }

    public void deleteInstrument(Integer id) {
        Instrument instrument = em.find(Instrument.class, id);
        if (instrument != null) {
            em.remove(instrument);
        }
    }




}
