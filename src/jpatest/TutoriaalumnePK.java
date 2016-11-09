/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpatest;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Kerinvel
 */
@Embeddable
public class TutoriaalumnePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "codiTutoria")
    private int codiTutoria;
    @Basic(optional = false)
    @Column(name = "codiAlumne")
    private int codiAlumne;

    public TutoriaalumnePK() {
    }

    public TutoriaalumnePK(int codiTutoria, int codiAlumne) {
        this.codiTutoria = codiTutoria;
        this.codiAlumne = codiAlumne;
    }

    public int getCodiTutoria() {
        return codiTutoria;
    }

    public void setCodiTutoria(int codiTutoria) {
        this.codiTutoria = codiTutoria;
    }

    public int getCodiAlumne() {
        return codiAlumne;
    }

    public void setCodiAlumne(int codiAlumne) {
        this.codiAlumne = codiAlumne;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codiTutoria;
        hash += (int) codiAlumne;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TutoriaalumnePK)) {
            return false;
        }
        TutoriaalumnePK other = (TutoriaalumnePK) object;
        if (this.codiTutoria != other.codiTutoria) {
            return false;
        }
        if (this.codiAlumne != other.codiAlumne) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpatest.TutoriaalumnePK[ codiTutoria=" + codiTutoria + ", codiAlumne=" + codiAlumne + " ]";
    }
    
}
