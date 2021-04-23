package org.launchcode.uTrain.models;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;



@Entity
public class User {


    @Id
//    @Email
//    @NotEmpty
    @Column(unique=true)
    private String email;

    @NotNull
    private String name;
//    @Size
    private String password;
    @OneToMany(mappedBy="user", cascade=CascadeType.ALL)
    private List<Task> tasks;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="USER_ROLES",joinColumns= {
            @JoinColumn(name="USER_EMAIL",referencedColumnName= "email")
    },inverseJoinColumns= {  @JoinColumn(name="USER_EMAIL",referencedColumnName= "email")})
    private List<Role> roles;

    public User(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public User(){};

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
