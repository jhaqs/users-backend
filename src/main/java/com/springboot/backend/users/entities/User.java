package com.springboot.backend.users.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.springboot.backend.users.models.IUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
@Getter
@Setter
public class User implements IUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    @NotBlank
    private String name;

    @Column(name="lastname")
    @NotBlank
    private String lastname;

    @Column(name="email")
    @Email
    @NotBlank
    private String email;

    @Column(name="username")
    @NotBlank
    @Size(min=4, max=12)
    private String username;

    @Transient//no esta mapeado a la tabla
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean admin;

    @Column(name="password")
    @NotBlank
    private String password;

    @JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="users_roles",
            joinColumns = {@JoinColumn(name="user_id")},
            inverseJoinColumns = {@JoinColumn(name="role_id")},
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role_id"})}
    )
    private List<Role> roles;

    //se inicializa roles en el constructor
    public User() {
        this.roles = new ArrayList<>();
    }

    //el metodo isAdmin es implementado de la interfaz IUser. Lombook ya lo tiene construido
    /*
    public boolean isAdmin(){
        return admin;
    }*/
}
