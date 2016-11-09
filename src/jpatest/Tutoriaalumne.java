/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpatest;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kerinvel
 */
@Entity
@Table(name = "tutoriaalumne")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tutoriaalumne.findAll", query = "SELECT t FROM Tutoriaalumne t"),
    @NamedQuery(name = "Tutoriaalumne.findByCodiTutoria", query = "SELECT t FROM Tutoriaalumne t WHERE t.tutoriaalumnePK.codiTutoria = :codiTutoria"),
    @NamedQuery(name = "Tutoriaalumne.findByCodiAlumne", query = "SELECT t FROM Tutoriaalumne t WHERE t.tutoriaalumnePK.codiAlumne = :codiAlumne"),
    @NamedQuery(name = "Tutoriaalumne.findByActiu", query = "SELECT t FROM Tutoriaalumne t WHERE t.actiu = :actiu")})
public class Tutoriaalumne implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TutoriaalumnePK tutoriaalumnePK;
    @Basic(optional = false)
    @Column(name = "actiu")
    private boolean actiu;
    @JoinColumn(name = "codiTutoria", referencedColumnName = "codi", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Tutoria tutoria;
    @JoinColumn(name = "codiAlumne", referencedColumnName = "codi", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Alumne alumne;

    public Tutoriaalumne() {
    }

    public Tutoriaalumne(TutoriaalumnePK tutoriaalumnePK) {
        this.tutoriaalumnePK = tutoriaalumnePK;
    }

    public Tutoriaalumne(TutoriaalumnePK tutoriaalumnePK, boolean actiu) {
        this.tutoriaalumnePK = tutoriaalumnePK;
        this.actiu = actiu;
    }

    public Tutoriaalumne(int codiTutoria, int codiAlumne) {
        this.tutoriaalumnePK = new TutoriaalumnePK(codiTutoria, codiAlumne);
    }

    public TutoriaalumnePK getTutoriaalumnePK() {
        return tutoriaalumnePK;
    }

    public void setTutoriaalumnePK(TutoriaalumnePK tutoriaalumnePK) {
        this.tutoriaalumnePK = tutoriaalumnePK;
    }

    public boolean getActiu() {
        return actiu;
    }

    public void setActiu(boolean actiu) {
        this.actiu = actiu;
    }

    public Tutoria getTutoria() {
        return tutoria;
    }

    public void setTutoria(Tutoria tutoria) {
        this.tutoria = tutoria;
    }

    public Alumne getAlumne() {
        return alumne;
    }

    public void setAlumne(Alumne alumne) {
        this.alumne = alumne;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tutoriaalumnePK != null ? tutoriaalumnePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tutoriaalumne)) {
            return false;
        }
        Tutoriaalumne other = (Tutoriaalumne) object;
        if ((this.tutoriaalumnePK == null && other.tutoriaalumnePK != null) || (this.tutoriaalumnePK != null && !this.tutoriaalumnePK.equals(other.tutoriaalumnePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpatest.Tutoriaalumne[ tutoriaalumnePK=" + tutoriaalumnePK + " ]";
    }
    
}
