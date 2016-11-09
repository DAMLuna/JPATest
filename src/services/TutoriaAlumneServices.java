/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import jpatest.Tutoriaalumne;

/**
 *
 * @author kerinvel
 */
public class TutoriaAlumneServices {
    protected EntityManager em;

    public TutoriaAlumneServices(EntityManager em) {
        this.em = em;
    }

    public void removeAlumne(int id) {
        Tutoriaalumne emp = findTutoriaalumne(id);
        if (emp != null) {
            em.remove(emp);
        }
    }

    public void addTutoriaalumne(Integer i, String nom) {
        Tutoriaalumne ta = new Tutoriaalumne();
        EntityTransaction tx = em.getTransaction();       
//        ta.setTutoria(tutoria);
        try {
            tx.begin();
            em.persist(ta);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    public Tutoriaalumne findTutoriaalumne(int id) {
        return em.find(Tutoriaalumne.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Tutoriaalumne> findAllTutorias() {
        Query query = em.createQuery("SELECT ta FROM Tutoriaalumne ta");
        return (List<Tutoriaalumne>) query.getResultList();
    }
}
