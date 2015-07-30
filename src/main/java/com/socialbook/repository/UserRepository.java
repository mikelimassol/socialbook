package com.socialbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.socialbook.entity.User;

/**
 * 
 * @author Michael Papamichael
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    /**
     * 
     * @param email String
     * @return User
     */
    User findUserByEmail(String email);

    /**
     * 
     * @param email String  
     * @param password String
     * @return User
     */
    User findUserByEmailAndPassword(String email, String password);
    
    
    
}
