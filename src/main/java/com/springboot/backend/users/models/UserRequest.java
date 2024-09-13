package com.springboot.backend.users.models;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest implements IUser{
    @NotBlank
    private String name;

    @NotBlank
    private String lastname;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min=4, max=12)
    private String username;

    private boolean admin;

    //el metodo isAdmin es implementado de la interfaz IUser. Lombook ya lo tiene construido
    /*
    public boolean isAdmin(){
        return admin;
    }*/

    /*
    public void setAdmin(boolean admin){
        this.admin=admin;
    }*/

}
