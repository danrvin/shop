package by.itstep.shop.dao.model;

import by.itstep.shop.dao.model.enums.Role;
import by.itstep.shop.dao.model.enums.Status;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "money", columnDefinition = "double default 0")
    private Double money;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private Status status;

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}
