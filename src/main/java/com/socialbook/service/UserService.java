package com.socialbook.service;

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
     * @return authenticated Users profile
     */
    User getAuthenticatedUserProfile();

    
    /**
     * 
     */
    void subscribeUser(User user);

}
