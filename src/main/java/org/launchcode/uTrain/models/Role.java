package org.launchcode.uTrain.models;

import javax.persistence.ManyToMany;
import java.util.List;

public class Role {

private String name;

@ManyToMany(mappedBy="roles")
private List<User> users;

    public Role(String name, List<User> users) {
        this.name = name;
        this.users = users;
    }

    public Role(){};


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
