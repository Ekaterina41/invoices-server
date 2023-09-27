package com.myprojects.invoice.controller;

import com.myprojects.invoice.entity.AuthRequest;
import com.myprojects.invoice.entity.UserDAO;
import com.myprojects.invoice.service.UserService;
import com.myprojects.invoice.utils.JwtTokenUtil;
import com.myprojects.invoice.service.JwtUserDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for User entity and authentication management.
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class JwtAuthenticationController {

    private final UserService userService;
    private final UserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    /**
     * Authenticate user and get user token.
     * @param authRequest
     * @return
     */
    @Operation(summary = "Authenticate user and get user token")
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getUsername(), authRequest.getPassword()));

        if (authentication.isAuthenticated()) {
            final UserDetails userDetails = userDetailsService
                    .loadUserByUsername(authRequest.getUsername());

            final String token = jwtTokenUtil.generateToken(userDetails);

            return ResponseEntity.ok(token);
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }

    /**
     * Register new user.
     * @param user user data
     * @return saved user data
     */
    @Operation(summary = "Register new user.")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<UserDAO> saveUser(@RequestBody UserDAO user) {
        return ResponseEntity.ok(userService.save(user));
    }
}
