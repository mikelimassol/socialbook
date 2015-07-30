package com.socialbook.service;

import com.socialbook.auth.user.AuthenticatedUser;

public interface AuthenticationUserService {
    
    /**
     * 
     * @return  authenticated user 
     */
    public AuthenticatedUser getAuthenticatedUser();

}
