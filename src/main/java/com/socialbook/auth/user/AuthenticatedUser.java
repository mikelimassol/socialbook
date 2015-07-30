package com.socialbook.auth.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.socialbook.entity.Role;
import com.socialbook.entity.User;



/**
 * 
 * @author Michael Papamichael
 *
 */
public class AuthenticatedUser implements UserDetails {
    
    private static final long serialVersionUID = -8161069311959247280L;
    
    private User user;
    
    /**
     * 
     */
    public AuthenticatedUser(){
        
    }

    /**
     * 
     * @param user User
     */
    public AuthenticatedUser(final User user) {
        
           this.user = user;
               
    }

    /**
     * @return authorities
     */
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        Set<Role> userRoles = this.user.getRoles();
        
        if (userRoles != null) {
            for (Role role : userRoles) {
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
                authorities.add(authority);
            }
        }
        return authorities;
        
    }
    
    public List<String> getAuthorityValues(){
         Collection<? extends GrantedAuthority> authorities = getAuthorities();
         List<String> authNames = new ArrayList<String>();
         for(GrantedAuthority auth: authorities){
             authNames.add(auth.getAuthority());
         }
         return authNames;
    }

   /**
    * @return username
    */
    public String getUsername() {
        return this.user.getEmail();
    }

    /**
     *  @return boolean
     */
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * @return boolean
     */
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * @return boolean
     */
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * @return boolean
     */
    public boolean isEnabled() {
        return user.getIsEnabled();
    }
    
    public String getPassword(){
        return this.user.getPassword();
    }
    
    public User getUser(){
        return this.user;
    }

}
