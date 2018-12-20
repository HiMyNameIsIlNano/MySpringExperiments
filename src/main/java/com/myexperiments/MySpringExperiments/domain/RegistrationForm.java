package com.myexperiments.MySpringExperiments.domain;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotBlank;

@Data
public class RegistrationForm {

    @NotBlank(message = "The User must be set")
    private String username;

    @NotBlank(message = "The Password must be set")
    private String password;
    @NotBlank(message = "The Confirmed Password must be set")
    private String confirmedPassword;

    @NotBlank(message = "The Name Password must be set")
    private String fullname;

    @NotBlank(message = "The Street must be set")
    private String street;

    @NotBlank(message = "The City must be set")
    private String city;

    @NotBlank(message = "The State must be set")
    private String state;

    @NotBlank(message = "The Zip must be set")
    private String zip;

    @NotBlank(message = "The Phone must be set")
    private String phone;

    @Autowired
    public UserAccount toUser(PasswordEncoder passwordEncoder) {
        return new UserAccount(
                username,
                passwordEncoder.encode(password),
                fullname,
                street,
                city,
                state,
                zip,
                phone,
                true);
    }

}
