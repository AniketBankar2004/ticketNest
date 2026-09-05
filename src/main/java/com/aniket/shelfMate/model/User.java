package com.aniket.shelfMate.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users_table")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false,unique = true)
    private String userName;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false)
    private String password;
}
