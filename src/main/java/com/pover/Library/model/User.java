package com.pover.Library.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @NotNull
    private String first_name;
    @NotNull
    private String last_name;

    @NotNull
    @Column(unique = true)
    @Email(message = "Email must be valid")
    private String email;

    private String member_number;
}
