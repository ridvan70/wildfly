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
 * <code>Account</code> intends to ...
 *
 * @version 1.0 03.04.2015
 * @author <a href="mailto:ridvan.agar@commercebay.de">Rıdvan Ağar</a>
 */
@Entity
@Table(name = "accounts")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a"),
    @NamedQuery(name = "Account.findByAccId", query = "SELECT a FROM Account a WHERE a.accId = :accId"),
    @NamedQuery(name = "Account.findByAccDesc", query = "SELECT a FROM Account a WHERE a.accDesc = :accDesc"),
    @NamedQuery(name = "Account.findByAccClass", query = "SELECT a FROM Account a WHERE a.accClass = :accClass"),
    @NamedQuery(name = "Account.findBySalesforceId", query = "SELECT a FROM Account a WHERE a.salesforceId = :salesforceId")})
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "acc_id")
    private String accId;
    @Column(name = "acc_desc")
    private String accDesc;
    @Basic(optional = false)
    @Column(name = "acc_class")
    private Character accClass;
    @Column(name = "salesforce_id")
    private String salesforceId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ioAccount")
    private Collection<InOut> inOutCollection;

    public Account() {
    }

    public Account(String accId) {
        this.accId = accId;
    }

    public Account(String accId, Character accClass) {
        this.accId = accId;
        this.accClass = accClass;
    }

    public String getAccId() {
        return accId;
    }

    public void setAccId(String accId) {
        this.accId = accId;
    }

    public String getAccDesc() {
        return accDesc;
    }

    public void setAccDesc(String accDesc) {
        this.accDesc = accDesc;
    }

    public Character getAccClass() {
        return accClass;
    }

    public void setAccClass(Character accClass) {
        this.accClass = accClass;
    }

    public String getSalesforceId() {
        return salesforceId;
    }

    public void setSalesforceId(String salesforceId) {
        this.salesforceId = salesforceId;
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
        hash += (accId != null ? accId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        if ((this.accId == null && other.accId != null) || (this.accId != null && !this.accId.equals(other.accId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.crazybits.softi.model.Account[ accId=" + accId + " ]";
    }

}
