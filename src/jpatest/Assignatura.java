/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpatest;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Kerinvel
 */
@Entity
@Table(name = "assignatura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Assignatura.findAll", query = "SELECT a FROM Assignatura a"),
    @NamedQuery(name = "Assignatura.findByCodi", query = "SELECT a FROM Assignatura a WHERE a.codi = :codi"),
    @NamedQuery(name = "Assignatura.findByNom", query = "SELECT a FROM Assignatura a WHERE a.nom = :nom")})
public class Assignatura implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codi")
    private Integer codi;
    @Basic(optional = false)
    @Column(name = "nom")
    private String nom;
    @OneToMany(mappedBy = "codiAssignatura")
    private List<Tutoria> tutoriaList;

    public Assignatura() {
    }

    public Assignatura(Integer codi) {
        this.codi = codi;
    }

    public Assignatura(Integer codi, String nom) {
        this.codi = codi;
        this.nom = nom;
    }

    public Integer getCodi() {
        return codi;
    }

    public void setCodi(Integer codi) {
        this.codi = codi;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @XmlTransient
    public List<Tutoria> getTutoriaList() {
        return tutoriaList;
    }

    public void setTutoriaList(List<Tutoria> tutoriaList) {
        this.tutoriaList = tutoriaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codi != null ? codi.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Assignatura)) {
            return false;
        }
        Assignatura other = (Assignatura) object;
        if ((this.codi == null && other.codi != null) || (this.codi != null && !this.codi.equals(other.codi))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpatest.Assignatura[ codi=" + codi + " ]";
    }
    
}
