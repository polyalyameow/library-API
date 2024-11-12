package com.pover.Library.model;


import com.pover.Library.model.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "member_number")})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @NotNull
    private String first_name;
    @NotNull
    private String last_name;

    @NotNull
    @Email(message = "Email must be valid")
    private String email;

    @NotNull
    @Column(name = "member_number", unique = true)
    private String memberNumber;

    @Enumerated(EnumType.STRING)
    private Role role;
}
