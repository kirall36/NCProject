package com.arangodb.spring.demo.entity;

import com.arangodb.springframework.annotation.Document;
import org.hibernate.validator.constraints.Email;
import org.springframework.data.annotation.Id;

import java.util.Collection;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Document("user")
public class User {

    @Id
    private String id;

    @Pattern(regexp = "^[A-Za-z0-9_]+$")
    private String login;

    @NotNull
    @Size(min=8, max=32)
    private String password;


    @Email
    @NotNull
    private String email;

    @NotNull
    @Pattern(regexp = "^[0-9]+$")
    private String phone;

    @NotNull
    private Collection<Role> roles;

    public User() {
        super();
    }

    public User(final String login, final String password, final String email, final String phone)
    {
        super();
        this.login = login;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    public User(final String login, final String password, final String email, final String phone, final Collection<Role> roles) {
        super();
        this.login = login;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", login=" + login + ", password=" + password + ", email=" + email + ", phone=" + phone + ", roles=" + roles.toString() + "]";
    }

    //getter & setter

    public String getPassword() {
        return password;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

}
