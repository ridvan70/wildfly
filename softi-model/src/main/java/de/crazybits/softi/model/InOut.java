/*
 * CommerceBay GmbH
 *
 * Copyright (c) 2015 CommerceBay GmbH. All Rights Reserved
 *
 */

package de.crazybits.softi.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <code>InOut</code> intends to ...
 *
 * @version 1.0 03.04.2015
 * @author <a href="mailto:ridvan.agar@commercebay.de">Rıdvan Ağar</a>
 */
@Entity
@Table(name = "in_out")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InOut.findAll", query = "SELECT i FROM InOut i"),
    @NamedQuery(name = "InOut.findByIoId", query = "SELECT i FROM InOut i WHERE i.ioId = :ioId"),
    @NamedQuery(name = "InOut.findByIoDate", query = "SELECT i FROM InOut i WHERE i.ioDate = :ioDate"),
    @NamedQuery(name = "InOut.findByIoDesc", query = "SELECT i FROM InOut i WHERE i.ioDesc = :ioDesc"),
    @NamedQuery(name = "InOut.findByIoVat", query = "SELECT i FROM InOut i WHERE i.ioVat = :ioVat"),
    @NamedQuery(name = "InOut.findByIoOut", query = "SELECT i FROM InOut i WHERE i.ioOut = :ioOut"),
    @NamedQuery(name = "InOut.findByIoIn", query = "SELECT i FROM InOut i WHERE i.ioIn = :ioIn"),
    @NamedQuery(name = "InOut.findByIoTimestamp", query = "SELECT i FROM InOut i WHERE i.ioTimestamp = :ioTimestamp")})
public class InOut implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "io_id")
    private Long ioId;
    @Basic(optional = false)
    @Column(name = "io_date")
    @Temporal(TemporalType.DATE)
    private Date ioDate;
    @Basic(optional = false)
    @Column(name = "io_desc")
    private String ioDesc;
    @Basic(optional = false)
    @Column(name = "io_vat")
    private float ioVat;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "io_out")
    private Float ioOut;
    @Column(name = "io_in")
    private Float ioIn;
    @Basic(optional = false)
    @Column(name = "io_timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ioTimestamp;
    @JoinColumn(name = "io_payment_type", referencedColumnName = "pay_art")
    @ManyToOne(optional = false)
    private PaymentType ioPaymentType;
    @JoinColumn(name = "io_account", referencedColumnName = "acc_id")
    @ManyToOne(optional = false)
    private Account ioAccount;
    @JoinColumn(name = "io_currency", referencedColumnName = "cur_id")
    @ManyToOne(optional = false)
    private Currency ioCurrency;

    public InOut() {
    }

    public InOut(Long ioId) {
        this.ioId = ioId;
    }

    public InOut(Long ioId, Date ioDate, String ioDesc, float ioVat, Date ioTimestamp) {
        this.ioId = ioId;
        this.ioDate = ioDate;
        this.ioDesc = ioDesc;
        this.ioVat = ioVat;
        this.ioTimestamp = ioTimestamp;
    }

    public Long getIoId() {
        return ioId;
    }

    public void setIoId(Long ioId) {
        this.ioId = ioId;
    }

    public Date getIoDate() {
        return ioDate;
    }

    public void setIoDate(Date ioDate) {
        this.ioDate = ioDate;
    }

    public String getIoDesc() {
        return ioDesc;
    }

    public void setIoDesc(String ioDesc) {
        this.ioDesc = ioDesc;
    }

    public float getIoVat() {
        return ioVat;
    }

    public void setIoVat(float ioVat) {
        this.ioVat = ioVat;
    }

    public Float getIoOut() {
        return ioOut;
    }

    public void setIoOut(Float ioOut) {
        this.ioOut = ioOut;
    }

    public Float getIoIn() {
        return ioIn;
    }

    public void setIoIn(Float ioIn) {
        this.ioIn = ioIn;
    }

    public Date getIoTimestamp() {
        return ioTimestamp;
    }

    public void setIoTimestamp(Date ioTimestamp) {
        this.ioTimestamp = ioTimestamp;
    }

    public PaymentType getIoPaymentType() {
        return ioPaymentType;
    }

    public void setIoPaymentType(PaymentType ioPaymentType) {
        this.ioPaymentType = ioPaymentType;
    }

    public Account getIoAccount() {
        return ioAccount;
    }

    public void setIoAccount(Account ioAccount) {
        this.ioAccount = ioAccount;
    }

    public Currency getIoCurrency() {
        return ioCurrency;
    }

    public void setIoCurrency(Currency ioCurrency) {
        this.ioCurrency = ioCurrency;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ioId != null ? ioId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InOut)) {
            return false;
        }
        InOut other = (InOut) object;
        if ((this.ioId == null && other.ioId != null) || (this.ioId != null && !this.ioId.equals(other.ioId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.crazybits.softi.model.InOut[ ioId=" + ioId + " ]";
    }

}
