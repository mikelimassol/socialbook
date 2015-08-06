package com.socialbook.enumeration;

/**
 * 
 * @author Michael Papamichael
 *
 */
public enum Roles {
        ADMIN(1), USER(2);
        
        Integer id;
        
        Roles(Integer id) {
            this.id = id;
        }
        
        public Integer getId(){
            return this.id;
        }
}
