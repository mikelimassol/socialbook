package com.socialbook.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * 
 * @author Michael Papamichael
 * 
 */
@Entity
@Table(name = "users")
public class User implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -4166503380552955833L;

    /**
     *
     */
    private static final Integer PRIME_NUMBNER = 31;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "is_enabled")
    private Boolean isEnabled;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UserConnection> userConnections;
    
    @OneToMany(mappedBy = "connectetUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UserConnection> connectedUsers;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = { @JoinColumn(name = "users_id") }, inverseJoinColumns = { @JoinColumn(name = "roles_id") })
    private Set<Role> roles;

    /**
     * 
     */
    public User() {
        super();
    }

    /**
     * 
     * @param id param
     */
    public User(Integer id) {
        this.id = id;
    }

    /**
     * @return the id
     */
    public final Integer getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public final void setId(final Integer id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public final String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public final void setName(final String name) {
        this.name = name;
    }

    /**
     * @return the email
     */
    public final String getEmail() {
        return email;
    }

    /**
     * @param email
     *            the email to set
     */
    public final void setEmail(final String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public final String getPassword() {
        return password;
    }

    /**
     * @param password
     *            the password to set
     */
    public final void setPassword(final String password) {
        this.password = password;
    }

    /**
     * @return the roles
     */
    @JsonIgnore
    public final Set<Role> getRoles() {
        if (roles == null) {
            roles = new HashSet<Role>();
        }
        return roles;
    }

    /**
     * @param roles
     *            the roles to set
     */
    public final void setRoles(Set<Role> roles) {

        this.roles = roles;
    }

    /**
     * @return the isEnabled
     */
    public final Boolean getIsEnabled() {
        return isEnabled;
    }

    /**
     * @param isEnabled
     *            the isEnabled to set
     */
    public final void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    /**
     * @return the moviePurchases
     */
    @JsonIgnore
    public final Set<UserConnection> getUserConnections() {
        if (userConnections == null) {
            userConnections = new HashSet<UserConnection>();
        }
        return userConnections;
    }

    /**
     * @param moviePurchase
     *            the moviePurchases to set
     */
    public final void setUserConnections(Set<UserConnection> userConnections) {
        this.userConnections = userConnections;
    }
    

    /**
     * @return the connectedUsers
     */
    @JsonIgnore
    public final Set<UserConnection> getConnectedUsers() {
        if (connectedUsers == null) {
            connectedUsers = new HashSet<UserConnection>();
        }
        return connectedUsers;
    }

    /**
     * @param connectedUsers the connectedUsers to set
     */
    public final void setConnectedUsers(Set<UserConnection> connectedUsers) {
        this.connectedUsers = connectedUsers;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = PRIME_NUMBNER;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof User)) {
            return false;
        }
        User other = (User) obj;
        if (email == null) {
            if (other.email != null) {
                return false;
            }
        } else if (!email.equals(other.email)) {
            return false;
        }
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", email=" + email
                + ", password=" + password + ", roles=" + roles
                + ", userConnections=" + userConnections + "]";
    }

}
