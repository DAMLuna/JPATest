/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpatest;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "tutoria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tutoria.findAll", query = "SELECT t FROM Tutoria t"),
    @NamedQuery(name = "Tutoria.findByCodi", query = "SELECT t FROM Tutoria t WHERE t.codi = :codi"),
    @NamedQuery(name = "Tutoria.findByNom", query = "SELECT t FROM Tutoria t WHERE t.nom = :nom"),
    @NamedQuery(name = "Tutoria.findByMaxParticipants", query = "SELECT t FROM Tutoria t WHERE t.maxParticipants = :maxParticipants")})
public class Tutoria implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codi")
    private Integer codi;
    @Basic(optional = false)
    @Column(name = "nom")
    private String nom;
    @Basic(optional = false)
    @Column(name = "maxParticipants")
    private int maxParticipants;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tutoria")
    private List<Tutoriaalumne> tutoriaalumneList;
    @JoinColumn(name = "codiAssignatura", referencedColumnName = "codi")
    @ManyToOne
    private Assignatura codiAssignatura;

    public Tutoria() {
    }

    public Tutoria(Integer codi) {
        this.codi = codi;
    }

    public Tutoria(Integer codi, String nom, int maxParticipants) {
        this.codi = codi;
        this.nom = nom;
        this.maxParticipants = maxParticipants;
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

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    @XmlTransient
    public List<Tutoriaalumne> getTutoriaalumneList() {
        return tutoriaalumneList;
    }

    public void setTutoriaalumneList(List<Tutoriaalumne> tutoriaalumneList) {
        this.tutoriaalumneList = tutoriaalumneList;
    }

    public Assignatura getCodiAssignatura() {
        return codiAssignatura;
    }

    public void setCodiAssignatura(Assignatura codiAssignatura) {
        this.codiAssignatura = codiAssignatura;
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
        if (!(object instanceof Tutoria)) {
            return false;
        }
        Tutoria other = (Tutoria) object;
        if ((this.codi == null && other.codi != null) || (this.codi != null && !this.codi.equals(other.codi))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpatest.Tutoria[ codi=" + codi + " ]";
    }
    
}
