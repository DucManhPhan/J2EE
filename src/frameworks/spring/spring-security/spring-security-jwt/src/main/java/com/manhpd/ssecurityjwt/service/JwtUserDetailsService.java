package com.manhpd.ssecurityjwt.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final static String DEFAULT_USER_NAME = "manhpd";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (DEFAULT_USER_NAME.equals(username)) {
            return new User(DEFAULT_USER_NAME, "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6", new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + DEFAULT_USER_NAME);
        }
    }

}
