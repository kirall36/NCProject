package com.arangodb.spring.demo.entity;

import com.arangodb.springframework.annotation.Document;
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
    @NotNull
    private Collection<Role> roles;

    public User() {
        super();
    }

    public User(final String login, final String password)
    {
        super();
        this.login = login;
        this.password = password;
    }

    public User(final String login, final String password, final Collection<Role> roles) {
        super();
        this.login = login;
        this.password = password;
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", login=" + login + ", password=" + password + ", roles=" + roles.toString() + "]";
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

    public String getId() {
        return id;
    }

}
