package by.itstep.shop.dao.model;

//import by.itstep.itemwar.itemwar.dao.model.enums.Role;
//import org.springframework.security.core.GrantedAuthority;


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
    private Long money;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private Status status;


//    @Column(name = "inventory")
//    private boolean inventory;
//
//    public boolean isInventory() {
//        return inventory;
//    }
//
//    public void setInventory(boolean inventory) {
//        this.inventory = inventory;
//    }

    public Long getId() {
        return id;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public void setId(Long id) {
        this.id = id;
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

    //    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    private String username;
//
//    private String password;
//
//    private boolean inventory;
//
//    private Long money;
//
//    private boolean active;
//
////    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
////    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
////    @Enumerated(EnumType.STRING)
////    private Set<Role> roles;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public boolean isInventory() {
//        return inventory;
//    }
//
//    public void setInventory(boolean inventory) {
//        this.inventory = inventory;
//    }
//
//    public Long getMoney() {
//        return money;
//    }
//
//    public void setMoney(Long money) {
//        this.money = money;
//    }
//
//    public boolean isActive() {
//        return active;
//    }
//
//    public void setActive(boolean active) {
//        this.active = active;
//    }
////
////    public Set<Role> getRoles() {
////        return roles;
////    }
////
////    public void setRoles(Set<Role> roles) {
////        this.roles = roles;
////    }
//
//    public User(String username, String password) {
//        this.username = username;
//        this.password = password;
//    }
//
//    public User() {
//    }
}
