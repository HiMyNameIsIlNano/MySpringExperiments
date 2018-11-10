package com.myexperiments.springmvc.domain.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Spitter {

    private Long id;

    @NotNull
    @Size(min=5, max=16, message = "{username.size}")
    private String username;

    @NotNull
    @Size(min=5, max=25)
    private String password;

    @NotNull
    private String email;

    @NotNull
    @Size(min=2, max=30)
    private String firstName;

    @NotNull
    @Size(min=2, max=30)
    private String lastName;

    private MultipartFile multipartFile;

    private String image;

    public Spitter() {
    }

    public Spitter(String username,
                   String password,
                   String email,
                   String firstName,
                   String lastName) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Spitter(Long id,
                   String username,
                   String password,
                   String email,
                   String firstName,
                   String lastName) {
        this(username, password, email, firstName, lastName);
        this.id = id;
    }

    public Spitter(String username,
                   String password,
                   String email,
                   String firstName,
                   String lastName,
                   MultipartFile multipartFile,
                   String image) {
        this(username, password, email, firstName, lastName);
        this.multipartFile = multipartFile;
        this.image = image;
    }

    public Spitter(Long id,
                   String username,
                   String password,
                   String email,
                   String firstName,
                   String lastName,
                   MultipartFile multipartFile,
                   String image) {
        this(id, username, password, email, firstName, lastName);
        this.multipartFile = multipartFile;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object that) {
        return EqualsBuilder.reflectionEquals(this, that, "id");
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, "id");
    }
}
