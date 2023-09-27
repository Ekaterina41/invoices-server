package com.myprojects.invoice.service;

import com.myprojects.invoice.entity.UserDAO;

/**
 * Service for operations with UserDAO entity.
 */
public interface UserService {

    /**
     * Save user.
     * @param user
     * @return saved user
     */
    UserDAO save(UserDAO user);
}
