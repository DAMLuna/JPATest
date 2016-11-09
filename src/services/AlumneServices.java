/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import jpatest.Alumne;

/**
 *
 * @author Kerinvel
 */
public class AlumneServices {

    protected EntityManager em;

    public AlumneServices(EntityManager em) {
        this.em = em;
    }

    public void removeAlumne(int id) {
        Alumne emp = findAlumne(id);
        if (emp != null) {
            em.remove(emp);
        }
    }

    public void addAlumne(Integer i, String nom) {
        Alumne a = new Alumne();
        EntityTransaction tx = em.getTransaction();
        a.setCodi(i);
        a.setNom(nom);
        try {
            tx.begin();
            em.persist(a);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    public Alumne findAlumne(int id) {
        return em.find(Alumne.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Alumne> findAllAlumne() {
        Query query = em.createQuery("SELECT e FROM Alumne e");
        return (List<Alumne>) query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Alumne> findNameAlumne(int codi) {
//        Query query = em.createQuery("SELECT e FROM Alumne e WHERE codi="+codi);
//        query.setParameter("codi", codi);
        TypedQuery<Alumne> query = em.createQuery("SELECT e FROM Alumne e WHERE e.codi= :codi", Alumne.class);
        query.setParameter("codi", codi);
        return (List<Alumne>) query.getResultList();
    }
}
