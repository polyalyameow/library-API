package com.pover.Library.model;


import com.pover.Library.model.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "admins")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long admin_id;

    @Column(unique = true, nullable = false)
    private String username;

    @NotNull
    private String password;



    @Enumerated(EnumType.STRING)
    private Role role;

}
