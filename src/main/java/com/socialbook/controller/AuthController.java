package com.socialbook.controller;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.socialbook.auth.user.AuthenticatedUser;




/**
 * 
 * @author Michael Papamichael
 *
 */
@Controller
@RequestMapping("/auth")
public class AuthController {
        
    /**
     * 
     * @return List or roles
     */
    @RequestMapping(value = "/session", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public AuthenticatedUser  getAuthUser() {
        return  ((AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }


}