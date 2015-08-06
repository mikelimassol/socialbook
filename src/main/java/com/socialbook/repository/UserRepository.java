package com.socialbook.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
   
    
    /**
     * 
     * @return all users and their connections
     */
    @Query("SELECT DISTINCT u FROM User u LEFT OUTER JOIN FETCH u.userConnections uc ")
    List<User> findAllUsersAndTheirConnections();
    
    /**
     * 
     * @return all users and their connections
     */
    @Query("SELECT DISTINCT u FROM User u INNER JOIN u.connectedUsers cu LEFT OUTER JOIN FETCH u.userConnections uc where cu.user = :user")
    List<User> findUsersAllConnections(@Param("user") User user);
    

    /**
     * 
     * @return all users and their connections
     */
    @Query("SELECT DISTINCT usr FROM User usr WHERE usr NOT IN "
            + "(SELECT DISTINCT u FROM User u INNER JOIN u.userConnections uc WHERE uc.user = :user) "
            + " AND usr != :user ")
    Page<User> findAllNotConnectedUsers(@Param("user") User user, Pageable pageable);
    
    
        
}
