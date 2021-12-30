package com.solvd.airport.models;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "users")
public class Users {
    private List<User> users;

    @XmlElement(name="user")
    public List<User> getUsers(){
        return users;
    }

    public void setUsers(List<User> users)
    {
        this.users = users;
    }
}
