package com.socialbook.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author Michael Papamichael
 *
 */
@Entity
@Table(name = "user_connections")
public class UserConnection implements Serializable {

    private static final long serialVersionUID = 215735653132984571L;

    private static final Integer PRIME_NUMBNER = 31;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "connected_user_id")
    private User connectetUser;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "date_connected")
    private Date dateConnected;

    /**
     * @return the id
     */
    public final Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public final void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the user
     */
    @JsonIgnore
    public final User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public final void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the connectetUser
     */
    public final User getConnectetUser() {
        return connectetUser;
    }

    /**
     * @param connectetUser the connectetUser to set
     */
    public final void setConnectetUser(User connectetUser) {
        this.connectetUser = connectetUser;
    }

    /**
     * @return the dateConnected
     */
    public final Date getDateConnected() {
        return dateConnected;
    }

    /**
     * @param dateConnected the dateConnected to set
     */
    public final void setDateConnected(Date dateConnected) {
        this.dateConnected = dateConnected;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = PRIME_NUMBNER;
        int result = 1;
        result = prime * result
                + ((connectetUser == null) ? 0 : connectetUser.hashCode());
        result = prime * result
                + ((dateConnected == null) ? 0 : dateConnected.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        return result;
    }

    /* (non-Javadoc)
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
        if (!(obj instanceof UserConnection)) {
            return false;
        }
        UserConnection other = (UserConnection) obj;
        if (connectetUser == null) {
            if (other.connectetUser != null) {
                return false;
            }
        } else if (!connectetUser.equals(other.connectetUser)) {
            return false;
        }
        if (dateConnected == null) {
            if (other.dateConnected != null) {
                return false;
            }
        } else if (!dateConnected.equals(other.dateConnected)) {
            return false;
        }
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (user == null) {
            if (other.user != null) {
                return false;
            }
        } else if (!user.equals(other.user)) {
            return false;
        }
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "UserConnection [id=" + id + ", user=" + user.getId()
                + ", connectetUser=" + connectetUser.getId() + ", dateConnected="
                + dateConnected + "]";
    }
    

    
    

    
    

    
    

}
