package com.socialbook.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.socialbook.auth.user.AuthenticatedUser;
import com.socialbook.entity.Role;
import com.socialbook.entity.User;
import com.socialbook.enumeration.Roles;
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
    public User findUserByEmailAndPassword(final String email,
            final String password) {
        return userRepository.findUserByEmailAndPassword(email, password);
    }

    @Override
    @Secured("ROLE_USER")
    public User getAuthenticatedUserProfile() {
        AuthenticatedUser authUser = authenticatedUserService
                .getAuthenticatedUser();
        if (authUser != null) {
            return userRepository.findOne(authUser.getUser().getId());
        }
        return null;
    }

    @Override
    public void subscribeUser(User user) {
        Role role = new Role();
        role.setId(Roles.USER.getId());
        user.getRoles().add(role);
        user.setIsEnabled(Boolean.TRUE);
        userRepository.save(user);
    }

    @Override
    @Secured("ROLE_USER")
    public List<User> getAllUsers() {
        return userRepository.findAll();

    }

    @Override
    @Secured("ROLE_ADMIN")
    public List<User> getAllUsersAndTheirConnections() {
        return userRepository.findAllUsersAndTheirConnections();

    }

    @Override
    @Secured({ "ROLE_USER", "ROLE_ADMIN" })
    public List<User> getAllConnections() {
        AuthenticatedUser authUser = authenticatedUserService
                .getAuthenticatedUser();
        return userRepository.findAllConnectedUsers(authUser.getUser());

    }

    @Override
    @Secured({ "ROLE_USER", "ROLE_ADMIN" })
    public List<User> getAllConnections(User user) {
        return userRepository.findAllConnectedUsers(user);
    }

    @Override
    @Secured({ "ROLE_USER", "ROLE_ADMIN" })
    public List<User> findAllNotConnectedUsers() {
        final AuthenticatedUser authUser = authenticatedUserService
                .getAuthenticatedUser();
        return userRepository.findAllNotConnectedUsers(authUser.getUser());
    }
}
