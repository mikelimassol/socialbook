package com.socialbook.controller;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory
            .getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserConnectionService userConnectionService;

    /**
     * 
     * @return all the users
     */
    @RequestMapping(value = "/secure/admin/browse/users", method = RequestMethod.GET)
    public List<User> getAll() {
        try {
            return userService.getAllUsersAndTheirConnections();
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            return Arrays.asList(new User());
        }
    }

    /**
     * 
     * @return the profile of the logged in user
     */
    @RequestMapping(value = "/secure/profile", method = RequestMethod.GET)
    public User getProfile() {
        try {
            return userService.getAuthenticatedUserProfile();
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            return new User();
        }
    }

    /**
     * @Returns the users connections
     */
    @RequestMapping(value = "/secure/browse/connections", method = RequestMethod.GET)
    public List<User> getConnections() {
        try {
            return userService.getAllConnections();
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            return Arrays.asList(new User());
        }
    }

    /**
     * 
     * @param userID
     *            of the user we want the connections to return
     * @return all the connections
     */
    @RequestMapping(value = "/secure/browse/connections/{userId}", method = RequestMethod.GET)
    public List<User> getConnections(
            @PathVariable(value = "userId") Integer userID) {
        try {
            return userService.getAllConnections(new User(userID));
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            return Arrays.asList(new User());
        }
    }

    /**
     * 
     * @param user
     *            we want to connect with
     * @return response if user was connected or not
     */
    @RequestMapping(value = "/secure/connect", consumes = { "application/json;charset=UTF-8" }, produces = { "application/json;charset=UTF-8" }, method = RequestMethod.POST)
    public ResponseEntity<User> connect(@RequestBody User user) {
        try {
            userConnectionService.createNewConnection(user);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            return new ResponseEntity<User>(user, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    /**
     * 
     * @return all the not connected users
     */
    @RequestMapping(value = "/secure/browse/users", method = RequestMethod.GET)
    public List<User> browseUsers() {
        try {
            return userService.findAllNotConnectedUsers();
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            return Arrays.asList(new User());
        }
    }

    /**
     * 
     * @param user
     *            we want to subscribe
     * @return response if subscription is successful
     */
    @RequestMapping(value = "/subcribe", consumes = { "application/json;charset=UTF-8" }, produces = { "application/json;charset=UTF-8" }, method = RequestMethod.POST)
    public ResponseEntity<User> subscribeUser(@RequestBody User user) {

        try {
            userService.subscribeUser(user);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            return new ResponseEntity<User>(user, HttpStatus.NOT_ACCEPTABLE);
        }

    }

}
