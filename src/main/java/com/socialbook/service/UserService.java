package com.socialbook.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.socialbook.entity.User;

public interface UserService {

    /**
     * 
     * @param email
     *            String
     * @return User
     */
    User findUserByEmail(String email);

    /**
     * 
     * @param email
     *            String
     * @param password
     *            String
     * @return User
     */
    User findUserByEmailAndPassword(String email, String password);

    /**
     * 
     * @return all users
     */
    List<User> getAllUsers();

    /**
     * 
     * @return all users with their connections
     */
    List<User> getAllUsersAndTheirConnections();

    /**
     * 
     * @return all of the users connections
     */
    List<User> getAllConnections();
    
    
    /**
     * 
     * @param user
     * @return all of the users connections
     */
    List<User> getAllConnections(User user);

    /**
     * 
     * @return authenticated Users profile
     */
    User getAuthenticatedUserProfile();

    /**
     * 
     */
    void subscribeUser(User user);
    
    
    /**
     * 
     * @param user
     * @return all not connected users
     */
    List<User> findAllNotConnectedUsers();
}
