package com.socialbook.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.socialbook.entity.User;
import com.socialbook.service.UserService;
import com.socialbook.service.impl.AuthenticationUserServiceImpl;

/**
 * 
 * @author Michael Papamichael
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class AuthenticationUserServiceImplTest {
    
    @InjectMocks
    AuthenticationUserServiceImpl service = new AuthenticationUserServiceImpl();
    
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
    public void shouldLoadUserByUsernameTest(){
        
        User user = new User();
        user.setName("Michael");
        user.setEmail("mikelimassol@hotmail.com");
        user.setPassword("12345678");
        
        when(userService.findUserByEmail(Matchers.anyString())).thenReturn(user);
        
        UserDetails userDetails = service.loadUserByUsername("");
        
        assertEquals(user.getEmail(), userDetails.getUsername());
        
        verify(userService, times(1)).findUserByEmail(Matchers.anyString());
        
    }
    
    
    @Test(expected = UsernameNotFoundException.class)
    public void shouldNotLoadUserByUsernameTest(){
        
        when(userService.findUserByEmail(Matchers.anyString())).thenReturn(null);
      
        UserDetails userDetails = service.loadUserByUsername("");
        
        verifyNoMoreInteractions(userService);
    }

    

}
