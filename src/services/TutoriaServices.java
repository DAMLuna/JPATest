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
import jpatest.Tutoria;

/**
 *
 * @author kerinvel
 */
public class TutoriaServices {
    protected EntityManager em;

    public TutoriaServices(EntityManager em) {
        this.em = em;
    }

    public void removeAlumne(int id) {
        Tutoria emp = findTutoria(id);
        if (emp != null) {
            em.remove(emp);
        }
    }

    public void addTutoria(Integer i, String nom) {
        Tutoria t = new Tutoria();
        EntityTransaction tx = em.getTransaction();       
        t.setCodi(i);
        t.setNom(nom);
//        a.setTutoriaList(tutoriaList);
        try {
            tx.begin();
            em.persist(t);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    public Tutoria findTutoria(int id) {
        return em.find(Tutoria.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Tutoria> findAllTutorias() {
        Query query = em.createQuery("SELECT t FROM Tutoria t");
        return (List<Tutoria>) query.getResultList();
    }
}
