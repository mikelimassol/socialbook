package com.socialbook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.socialbook.entity.User;
import com.socialbook.entity.UserConnection;

@Repository
public interface UserConnectionRepository extends
        JpaRepository<UserConnection, Integer> {

    /**
     * 
     * @param user
     *            object
     * @return user connections
     */
    List<UserConnection> findAllByUser(User user);
   
   
}
