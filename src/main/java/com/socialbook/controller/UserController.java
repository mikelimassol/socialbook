package com.socialbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    @RequestMapping(value = "/secure/admin/getAllUsers", method = RequestMethod.GET)
    public List<User> getAll() {
       return userService.getAllUsersAndTheirConnections();
    }
    
    
    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/secure/profile", method = RequestMethod.GET)
    public User getProfile() {
        return userService.getAuthenticatedUserProfile();
    }
    
    
    
    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/secure/connections", method = RequestMethod.GET)
    public List<User> getConnections() {
        return userService.getAllConnections();
    }
    
    
    /**
     * Used to subscribe new user
     */
    @RequestMapping(value = "/secure/connect", method = RequestMethod.POST)   
    public void connect(@RequestBody  User user) {
       userService.subscribeUser(user);
    }
    
    
    /**
     * Used to connect to user
     */
    @RequestMapping(value = "/secure/browse/users", method = RequestMethod.GET)   
    public Page<User> browseUsers() {
        return userService.findAllNotConnectedUsers();
    }
    
    /**
     * Used to connect to user
     */
    @RequestMapping(value = "/subcribe", produces = "application/json", method = RequestMethod.POST)   
    public void subscribeUser(@RequestBody  User user) {
        userConnectionService.createNewConnection(user);
    }
    
}
