package com.socialbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.socialbook.entity.User;
import com.socialbook.service.UserConnectionService;
import com.socialbook.service.UserService;


/**
 * Handles requests for the application home page.
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private UserConnectionService userConnectionService;
    
    
    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public User profile() {
        return userService.getAuthenticatedUserProfile();
    }
    
    
    
    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/profile/users", method = RequestMethod.GET)
    public List<User> users() {
        return null;
    }
    

    
    
    /**
     * Used to subscribe move to subscriber.
     */
    @RequestMapping(value = "/subcribe", method = RequestMethod.POST)   
    public void movieSubscribe(@RequestBody  User user) {
       userService.subscribeUser(user);
    }
    
}
