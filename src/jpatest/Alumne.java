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
@Table(name = "alumne")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Alumne.findAll", query = "SELECT a FROM Alumne a"),
    @NamedQuery(name = "Alumne.findByCodi", query = "SELECT a FROM Alumne a WHERE a.codi = :codi"),
    @NamedQuery(name = "Alumne.findByNom", query = "SELECT a FROM Alumne a WHERE a.nom = :nom")})
public class Alumne implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codi")
    private Integer codi;
    @Basic(optional = false)
    @Column(name = "nom")
    private String nom;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "alumne")
    private List<Tutoriaalumne> tutoriaalumneList;

    public Alumne() {
    }

    public Alumne(Integer codi) {
        this.codi = codi;
    }

    public Alumne(Integer codi, String nom) {
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
    public List<Tutoriaalumne> getTutoriaalumneList() {
        return tutoriaalumneList;
    }

    public void setTutoriaalumneList(List<Tutoriaalumne> tutoriaalumneList) {
        this.tutoriaalumneList = tutoriaalumneList;
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
        if (!(object instanceof Alumne)) {
            return false;
        }
        Alumne other = (Alumne) object;
        if ((this.codi == null && other.codi != null) || (this.codi != null && !this.codi.equals(other.codi))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpatest.Alumne[ codi=" + codi + " ]";
    }
    
}
