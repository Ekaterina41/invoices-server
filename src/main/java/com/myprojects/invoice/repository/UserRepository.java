package com.myprojects.invoice.repository;

import com.myprojects.invoice.entity.UserDAO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for CRUD operations with User entities
 */
@Repository
public interface UserRepository extends CrudRepository<UserDAO, Integer> {
    UserDAO findByUsername(String username);
}
