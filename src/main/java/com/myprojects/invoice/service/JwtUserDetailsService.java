package com.myprojects.invoice.service;

import com.myprojects.invoice.entity.UserDAO;
import com.myprojects.invoice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Spring Security UserDetailsService implementation.
 */
@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDAO userDao = userRepository.findByUsername(username);

        if (userDao == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return new User(userDao.getUsername(), userDao.getPassword(), new ArrayList<>());
    }
}
