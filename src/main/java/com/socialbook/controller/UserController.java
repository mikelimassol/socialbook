package com.socialbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
    @RequestMapping(value = "/secure/admin/browse/users", method = RequestMethod.GET)
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
    @RequestMapping(value = "/secure/browse/connections", method = RequestMethod.GET)
    public List<User> getConnections() {
        return userService.getAllConnections();
    }
    
    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/secure/browse/connections/{userId}", method = RequestMethod.GET)
    public List<User> getConnections(@PathVariable(value = "userId") Integer userID) {
        return userService.getAllConnections(new User(userID));
    }
    
    
    /**
     * Used to subscribe new user
     */
    @RequestMapping(value = "/secure/connect",
            consumes = {"application/json;charset=UTF-8"}, 
            produces={"application/json;charset=UTF-8"}, 
            method = RequestMethod.POST)   
    public ResponseEntity<User> connect(@RequestBody  User user) {
       try {
           userConnectionService.createNewConnection(user);
           return new ResponseEntity<User>(user, HttpStatus.OK);
       } catch (Exception ex) {
           return new ResponseEntity<User>(user, HttpStatus.NOT_ACCEPTABLE);
       }
    }
    
    
    /**
     * Used to connect to user
     */
    @RequestMapping(value = "/secure/browse/users", method = RequestMethod.GET)   
    public List<User> browseUsers() {
        return userService.findAllNotConnectedUsers();
    }
    
    /**
     * Used to connect to user
     */
    @RequestMapping(value = "/subcribe",   
                    consumes = {"application/json;charset=UTF-8"}, 
                    produces={"application/json;charset=UTF-8"}, 
                    method = RequestMethod.POST)   
    public ResponseEntity<User> subscribeUser(@RequestBody  User user) {       
        
       try {         
            userService.subscribeUser(user);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } catch (Exception ex) {
          
            return new ResponseEntity<User>(user, HttpStatus.NOT_ACCEPTABLE);
        }
           
    }
    
}
