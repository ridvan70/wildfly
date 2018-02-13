/*
 * CommerceBay GmbH
 *
 * Copyright (c) 2015 CommerceBay GmbH. All Rights Reserved
 *
 */

package de.crazybits.softi.model;

import java.io.Serializable;
import java.util.Collection;
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
 * <code>Currency</code> intends to ...
 *
 * @version 1.0 03.04.2015
 * @author <a href="mailto:ridvan.agar@commercebay.de">Rıdvan Ağar</a>
 */
@Entity
@Table(name = "currencies")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Currency.findAll", query = "SELECT c FROM Currency c"),
    @NamedQuery(name = "Currency.findByCurId", query = "SELECT c FROM Currency c WHERE c.curId = :curId"),
    @NamedQuery(name = "Currency.findByCurDesc", query = "SELECT c FROM Currency c WHERE c.curDesc = :curDesc")})
public class Currency implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cur_id")
    private String curId;
    @Basic(optional = false)
    @Column(name = "cur_desc")
    private String curDesc;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ioCurrency")
    private Collection<InOut> inOutCollection;

    public Currency() {
    }

    public Currency(String curId) {
        this.curId = curId;
    }

    public Currency(String curId, String curDesc) {
        this.curId = curId;
        this.curDesc = curDesc;
    }

    public String getCurId() {
        return curId;
    }

    public void setCurId(String curId) {
        this.curId = curId;
    }

    public String getCurDesc() {
        return curDesc;
    }

    public void setCurDesc(String curDesc) {
        this.curDesc = curDesc;
    }

    @XmlTransient
    public Collection<InOut> getInOutCollection() {
        return inOutCollection;
    }

    public void setInOutCollection(Collection<InOut> inOutCollection) {
        this.inOutCollection = inOutCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (curId != null ? curId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Currency)) {
            return false;
        }
        Currency other = (Currency) object;
        if ((this.curId == null && other.curId != null) || (this.curId != null && !this.curId.equals(other.curId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.crazybits.softi.model.Currency[ curId=" + curId + " ]";
    }

}
