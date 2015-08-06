package com.socialbook.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role implements Serializable {
    
    
    private static final long serialVersionUID = 1L;
    
    private static final Integer PRIME_NUMBNER = 31;

    @Id
    @Column(name="id")
    private int id;
           
    @Column(name="name")
    private String name;

    //bi-directional many-to-many association to SystemUser
    @ManyToMany(mappedBy="roles", cascade={CascadeType.ALL}, fetch = FetchType.LAZY)
    private Set<User> users;

    /**
     * @return the id
     */
    public final int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public final void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public final String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public final void setName(String name) {
        this.name = name;
    }

    /**
     * @return the users
     */
    public final Set<User> getUsers() {
        return users;
    }

    /**
     * @param users the users to set
     */
    public final void setUsers(Set<User> users) {
        this.users = users;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = PRIME_NUMBNER;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        if (!(obj instanceof Role)) {
            return false;
        }
        Role other = (Role) obj;
        if (id != other.id) {
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

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Role [id=" + id + ", name=" + name + "]";
    }
    
    
   
}
