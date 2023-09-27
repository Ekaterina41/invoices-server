package com.myprojects.invoice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

/**
 * User entity.
 */
@Data
@Entity
@Table(name = "user_data")
public class UserDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_data_generator")
    @SequenceGenerator(name = "user_data_generator", sequenceName = "user_data_seq", allocationSize = 1)
    @Schema(description = "User ID", example = "1")
    private int id;

    @Schema(description = "User name", example = "My_User")
    private String username;

    @Schema(description = "Password", example = "****")
    private String password;
}
