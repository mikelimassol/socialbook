package com.socialbook.service;

import java.util.List;

import com.socialbook.entity.User;
import com.socialbook.entity.UserConnection;

public interface UserConnectionService {
    /**
     * 
     * @param user
     *            object
     * @return MoviePurchases
     */
    List<UserConnection> findAllByUser(User user);

    /**
     * @param user
     *            object 
     */
    void createNewConnection(User user);
}
