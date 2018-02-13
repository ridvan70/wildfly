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
 * <code>PaymentType</code> intends to ...
 *
 * @version 1.0 03.04.2015
 * @author <a href="mailto:ridvan.agar@commercebay.de">Rıdvan Ağar</a>
 */
@Entity
@Table(name = "payment_types")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PaymentType.findAll", query = "SELECT p FROM PaymentType p"),
    @NamedQuery(name = "PaymentType.findByPayArt", query = "SELECT p FROM PaymentType p WHERE p.payArt = :payArt"),
    @NamedQuery(name = "PaymentType.findBySalesforceId", query = "SELECT p FROM PaymentType p WHERE p.salesforceId = :salesforceId")})
public class PaymentType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "pay_art")
    private String payArt;
    @Column(name = "salesforce_id")
    private String salesforceId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ioPaymentType")
    private Collection<InOut> inOutCollection;

    public PaymentType() {
    }

    public PaymentType(String payArt) {
        this.payArt = payArt;
    }

    public String getPayArt() {
        return payArt;
    }

    public void setPayArt(String payArt) {
        this.payArt = payArt;
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
        hash += (payArt != null ? payArt.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PaymentType)) {
            return false;
        }
        PaymentType other = (PaymentType) object;
        if ((this.payArt == null && other.payArt != null) || (this.payArt != null && !this.payArt.equals(other.payArt))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.crazybits.softi.model.PaymentType[ payArt=" + payArt + " ]";
    }

}
