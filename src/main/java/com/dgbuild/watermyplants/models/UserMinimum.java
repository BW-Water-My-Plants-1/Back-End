package com.dgbuild.watermyplants.models;

import javax.validation.constraints.Email;

public class UserMinimum {
    private String username;

    private String password;

    @Email
    private String email;

    public UserMinimum() {
    }

    public UserMinimum(String username, String password, @Email String email) {
        this.username = username;
        this.password = password;
        this.email = email;
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
}
