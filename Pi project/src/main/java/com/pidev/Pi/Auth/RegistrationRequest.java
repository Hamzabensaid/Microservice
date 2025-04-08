package com.pidev.Pi.Auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegistrationRequest {

    @NotEmpty(message = "Firstname is mandatory")
    @NotBlank(message = "Firstname is mandatory ")
    private String firstname;
    @NotEmpty(message = "Lastname is mandatory")
    @NotBlank(message = "Lastname is mandatory ")
    private String lastname;
    @Email(message = "Email is not formatted")
    @NotEmpty(message = "Email is mandatory")
    @NotBlank(message = "Email is mandatory ")
    private String email;
    @NotEmpty(message = "Password is mandatory")
    @NotBlank(message = "Password is mandatory ")
    @Size(min = 8, message = "Password should be 8 characters long minimum")
    private String password;
    @NotEmpty(message = "Password confirmation is mandatory")
    @NotBlank(message = "Password confirmation is mandatory ")
    private String confirmPassword;

    public @NotEmpty(message = "Firstname is mandatory") @NotBlank(message = "Firstname is mandatory ") String getFirstname() {
        return firstname;
    }

    public void setFirstname(@NotEmpty(message = "Firstname is mandatory") @NotBlank(message = "Firstname is mandatory ") String firstname) {
        this.firstname = firstname;
    }

    public @NotEmpty(message = "Lastname is mandatory") @NotBlank(message = "Lastname is mandatory ") String getLastname() {
        return lastname;
    }

    public void setLastname(@NotEmpty(message = "Lastname is mandatory") @NotBlank(message = "Lastname is mandatory ") String lastname) {
        this.lastname = lastname;
    }

    public @Email(message = "Email is not formatted") @NotEmpty(message = "Email is mandatory") @NotBlank(message = "Email is mandatory ") String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "Email is not formatted") @NotEmpty(message = "Email is mandatory") @NotBlank(message = "Email is mandatory ") String email) {
        this.email = email;
    }

    public @NotEmpty(message = "Password is mandatory") @NotBlank(message = "Password is mandatory ") @Size(min = 8, message = "Password should be 8 characters long minimum") String getPassword() {
        return password;
    }

    public void setPassword(@NotEmpty(message = "Password is mandatory") @NotBlank(message = "Password is mandatory ") @Size(min = 8, message = "Password should be 8 characters long minimum") String password) {
        this.password = password;
    }
}
