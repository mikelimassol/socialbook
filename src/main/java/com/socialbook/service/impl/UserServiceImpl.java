package com.socialbook.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialbook.auth.user.AuthenticatedUser;
import com.socialbook.entity.User;
import com.socialbook.repository.UserRepository;
import com.socialbook.service.AuthenticationUserService;
import com.socialbook.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;
    
 
    
    @Autowired
    private AuthenticationUserService authenticatedUserService;

    @Override
    public User findUserByEmail(final String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public User findUserByEmailAndPassword(final String email, final String password) {
        return userRepository.findUserByEmailAndPassword(email, password);
    }
    
    
    @Override
    public User getAuthenticatedUserProfile() {
        AuthenticatedUser authUser = authenticatedUserService.getAuthenticatedUser();
        if(authUser != null){
            return userRepository.findOne(authUser.getUser().getId());
        }
        return null;
    }

    @Override
    public void subscribeUser(User user) {
        userRepository.save(user);
    }


    
    
 
    


}
