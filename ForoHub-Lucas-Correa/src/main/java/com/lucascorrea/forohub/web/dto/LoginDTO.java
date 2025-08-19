package com.lucascorrea.forohub.web.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
public class LoginDTO {
    @Email private String email;
    @NotBlank private String password;
    public String getEmail(){return email;} public void setEmail(String e){this.email=e;}
    public String getPassword(){return password;} public void setPassword(String p){this.password=p;}
}