package com.socialbook.service.impl;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.socialbook.auth.user.AuthenticatedUser;
import com.socialbook.entity.User;
import com.socialbook.repository.UserRepository;
import com.socialbook.service.AuthenticationUserService;

/**
 * 
 * @author Michael Papamichael
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
    
    @InjectMocks
    UserServiceImpl service = new  UserServiceImpl();
    
    @Mock
    private UserRepository userRepository;
      
    @Mock
    private AuthenticationUserService authenticatedUserService;
    
    /**
     * 
     */
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void shouldFindUserByEmail(){
        when(userRepository.findUserByEmail(Matchers.anyString())).thenReturn(new User());
        User user = service.findUserByEmail("");
        verify(userRepository, times(1)).findUserByEmail(Matchers.anyString());
    }
    
    
    
    @Test
    public void shouldFindUserByEmailAndPassword(){
        when(userRepository.findUserByEmailAndPassword(Matchers.anyString(),Matchers.anyString())).thenReturn(new User());
        User user = service.findUserByEmailAndPassword("", "");
        verify(userRepository, times(1)).findUserByEmailAndPassword(Matchers.anyString(),Matchers.anyString());
    }
    
    @Test
    public void shouldGetAuthenticatedUserProfile(){
        User user = new User();
        user.setId(0);
        AuthenticatedUser authUser = new AuthenticatedUser(user);
        
        
        when(authenticatedUserService.getAuthenticatedUser()).thenReturn(authUser);
        when(userRepository.findOne(Matchers.anyInt())).thenReturn(new User());
        
        service.getAuthenticatedUserProfile();
        
        verify(authenticatedUserService, times(1)).getAuthenticatedUser();
        verify(userRepository, times(1)).findOne(Matchers.anyInt());
        
    }
    
    
    @Test
    public void shouldNotGetAuthenticatedUserProfile(){
        
        when(authenticatedUserService.getAuthenticatedUser()).thenReturn(null);
        when(userRepository.findOne(Matchers.anyInt())).thenReturn(new User());
        
        service.getAuthenticatedUserProfile();
        
        verify(authenticatedUserService, times(1)).getAuthenticatedUser();
        verify(userRepository, times(0)).findOne(Matchers.anyInt());
        
    }
    
    
    @Test
    public void subscribeUser(){
        service.subscribeUser(new User());
        verify(userRepository, times(1)).save(Matchers.any(User.class));
    }
    
    @Test
    public void shouldGetAllUsers(){
        when(userRepository.findAll()).thenReturn(new ArrayList<User>());
        service.getAllUsers();
        verify(userRepository, times(1)).findAll();
    }
    
    @Test
    public void shouldGetAllUsersAndTheirConnections(){
        when(userRepository.findAllUsersAndTheirConnections()).thenReturn(new ArrayList<User>());
        service.getAllUsersAndTheirConnections();
        verify(userRepository, times(1)).findAllUsersAndTheirConnections();
    }


    
    @Test
    public void shouldGetAllConnections(){
        User user = new User();
        user.setId(0);
        AuthenticatedUser authUser = new AuthenticatedUser(user);
        when(authenticatedUserService.getAuthenticatedUser()).thenReturn(authUser); 
        when(userRepository.findAllConnectedUsers(Matchers.any(User.class))).thenReturn(new ArrayList<User>());
        
        service.getAllConnections();
        
        verify(authenticatedUserService, times(1)).getAuthenticatedUser();
        verify(userRepository, times(1)).findAllConnectedUsers(Matchers.any(User.class));
        
    }
    
}
