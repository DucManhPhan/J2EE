package com.manhpd.ssecurityjwt.controller;

import com.manhpd.ssecurityjwt.dto.request.JwtRequest;
import com.manhpd.ssecurityjwt.dto.response.JwtResponse;
import com.manhpd.ssecurityjwt.service.JwtUserDetailsService;
import com.manhpd.ssecurityjwt.utils.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) {
        this.authenticate(authenticationRequest);
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(authenticationRequest.getUserName());
        String token = this.jwtTokenUtils.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(JwtRequest request) {
        String username = request.getUserName();
        String password = request.getPassword();

        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        } catch (DisabledException ex) {
            throw new RuntimeException("USER_DISABLED", ex);
        } catch (BadCredentialsException ex) {
            throw new RuntimeException("INVALID_CREDENTIALS", ex);
        }
    }

}
