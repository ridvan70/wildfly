/*
 * CommerceBay GmbH
 *
 * Copyright (c) 2015 CommerceBay GmbH. All Rights Reserved
 *
 */

package de.crazybits.softi.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <code>User</code> intends to ...
 *
 * @version 1.0 03.04.2015
 * @author <a href="mailto:ridvan.agar@commercebay.de">Rıdvan Ağar</a>
 */
@Entity
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByUsrId", query = "SELECT u FROM User u WHERE u.usrId = :usrId"),
    @NamedQuery(name = "User.findByUsrFirstname", query = "SELECT u FROM User u WHERE u.usrFirstname = :usrFirstname"),
    @NamedQuery(name = "User.findByUsrLastname", query = "SELECT u FROM User u WHERE u.usrLastname = :usrLastname"),
    @NamedQuery(name = "User.findByUsrPwd", query = "SELECT u FROM User u WHERE u.usrPwd = :usrPwd"),
    @NamedQuery(name = "User.findByUsrEmail", query = "SELECT u FROM User u WHERE u.usrEmail = :usrEmail"),
    @NamedQuery(name = "User.findByUsrLanguage", query = "SELECT u FROM User u WHERE u.usrLanguage = :usrLanguage")})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "usr_id")
    private String usrId;
    @Basic(optional = false)
    @Column(name = "usr_firstname")
    private String usrFirstname;
    @Basic(optional = false)
    @Column(name = "usr_lastname")
    private String usrLastname;
    @Basic(optional = false)
    @Column(name = "usr_pwd")
    private String usrPwd;
    @Basic(optional = false)
    @Column(name = "usr_email")
    private String usrEmail;
    @Basic(optional = false)
    @Column(name = "usr_language")
    private String usrLanguage;

    public User() {
    }

    public User(String usrId) {
        this.usrId = usrId;
    }

    public User(String usrId, String usrFirstname, String usrLastname, String usrPwd, String usrEmail, String usrLanguage) {
        this.usrId = usrId;
        this.usrFirstname = usrFirstname;
        this.usrLastname = usrLastname;
        this.usrPwd = usrPwd;
        this.usrEmail = usrEmail;
        this.usrLanguage = usrLanguage;
    }

    public String getUsrId() {
        return usrId;
    }

    public void setUsrId(String usrId) {
        this.usrId = usrId;
    }

    public String getUsrFirstname() {
        return usrFirstname;
    }

    public void setUsrFirstname(String usrFirstname) {
        this.usrFirstname = usrFirstname;
    }

    public String getUsrLastname() {
        return usrLastname;
    }

    public void setUsrLastname(String usrLastname) {
        this.usrLastname = usrLastname;
    }

    public String getUsrPwd() {
        return usrPwd;
    }

    public void setUsrPwd(String usrPwd) {
        this.usrPwd = usrPwd;
    }

    public String getUsrEmail() {
        return usrEmail;
    }

    public void setUsrEmail(String usrEmail) {
        this.usrEmail = usrEmail;
    }

    public String getUsrLanguage() {
        return usrLanguage;
    }

    public void setUsrLanguage(String usrLanguage) {
        this.usrLanguage = usrLanguage;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usrId != null ? usrId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.usrId == null && other.usrId != null) || (this.usrId != null && !this.usrId.equals(other.usrId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.crazybits.softi.model.User[ usrId=" + usrId + " ]";
    }

}
