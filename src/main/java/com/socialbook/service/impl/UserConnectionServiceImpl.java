package com.socialbook.service.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialbook.auth.user.AuthenticatedUser;
import com.socialbook.entity.User;
import com.socialbook.entity.UserConnection;
import com.socialbook.repository.UserConnectionRepository;
import com.socialbook.service.AuthenticationUserService;
import com.socialbook.service.UserConnectionService;
import com.socialbook.service.UserService;

/**
 * 
 * @author Michael Papamichael
 *
 */
@Service
public class UserConnectionServiceImpl implements UserConnectionService {
    
    @Autowired
    private UserConnectionRepository userConnectionRepository;
    
    @Autowired
    private AuthenticationUserService authenticatedUserService;
    
    
    @Autowired
    private UserService userService;
    

    @Override
    public List<UserConnection> findAllByUser(User user) {
        return userConnectionRepository.findAllByUser(user);
    }
    
    
    @Override
    public void connectToUser(User user) {
        AuthenticatedUser authUser = authenticatedUserService.getAuthenticatedUser();
        if(authUser != null){
            Calendar cal = Calendar.getInstance();
            UserConnection userConnection = new UserConnection();
            userConnection.setUser(authUser.getUser());
            userConnection.setConnectetUser(user);
            userConnection.setDateConnected(cal.getTime());
            userConnectionRepository.save(userConnection);
            userConnectionRepository.flush();
        }
    }


    
}
