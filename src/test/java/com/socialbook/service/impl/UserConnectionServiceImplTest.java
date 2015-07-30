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

import com.socialbook.entity.User;
import com.socialbook.entity.UserConnection;
import com.socialbook.repository.UserConnectionRepository;
import com.socialbook.service.AuthenticationUserService;
import com.socialbook.service.UserService;


/**
 * 
 * @author Michael Papamichael
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class UserConnectionServiceImplTest {
    
    @InjectMocks
    UserConnectionServiceImpl service = new UserConnectionServiceImpl();
    
    @Mock
    private UserConnectionRepository userConnectionRepository;
    
    @Mock
    private AuthenticationUserService authenticatedUserService;
    
    
    @Mock
    private UserService userService;
    
   
    /**
     * 
     */
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void shouldFindAllByUserTest(){
        when(userConnectionRepository.findAllByUser(Matchers.any(User.class))).thenReturn(new ArrayList<UserConnection>());
        service.findAllByUser(new User());
        verify(userConnectionRepository, times(1)).findAllByUser(Matchers.any(User.class));
    }
    
    
}
