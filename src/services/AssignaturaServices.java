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
import javax.persistence.TypedQuery;
import jpatest.Alumne;
import jpatest.Assignatura;

/**
 *
 * @author kerinvel
 */
public class AssignaturaServices {

    protected EntityManager em;

    public AssignaturaServices(EntityManager em) {
        this.em = em;
    }

    public void removeAlumne(int id) {
        Assignatura emp = findAssignatura(id);
        if (emp != null) {
            em.remove(emp);
        }
    }

    public void addAssignatura(Integer i, String nom) {
        Assignatura a = new Assignatura();
        EntityTransaction tx = em.getTransaction();       
        a.setCodi(i);
        a.setNom(nom);
//        a.setTutoriaList(tutoriaList);
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

    public Assignatura findAssignatura(int id) {
        return em.find(Assignatura.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Assignatura> findAllAssignaturas() {
        Query query = em.createQuery("SELECT a FROM Assignatura a");
        return (List<Assignatura>) query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Assignatura> findAssignaturasAlumne(int cod) {
//        Query query = em.createQuery("SELECT e FROM Assignatura e,Tutoria t,Tutoriaalumne ta WHERE ta.codiTutoria=t.codi AND t.codiAssignatura=e.codi AND ta.codiAlumne=:codi GROUP BY ta.codiAlumne");
//        query.setParameter("codi", codi);
//          Select e from Alumne a, Assignatura e, Tutoria t, Tutoriaalumne ta where a.codi=ta.codiAlumne and t.codi=ta.codiTutoria and t.codiAssignatura=e.codi and a.codi=2 group by e.codi;
//        TypedQuery<Assignatura> query = em.createQuery("SELECT e FROM Assignatura e,Tutoria t,Tutoriaalumne ta WHERE ta.tutoriaalumnePK.codiTutoria=t.codi AND t.codiAssignatura=e.codi AND ta.tutoriaalumnePK.codiAlumne= :codi GROUP BY ta.tutoriaalumnePK.codiAlumne;", Assignatura.class);
        TypedQuery<Assignatura> query = em.createQuery("SELECT e FROM Alumne a, Assignatura e, Tutoria t, Tutoriaalumne ta WHERE  a.codi=ta.tutoriaalumnePK.codiAlumne AND t.codi=ta.tutoriaalumnePK.codiTutoria AND t.codiAssignatura=e.codi AND a.codi= :cod GROUP BY e.codi", Assignatura.class);
        query.setParameter("cod", cod);
        return (List<Assignatura>) query.getResultList();
    }
}
