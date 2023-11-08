package com.example.audiobook.entity;


import com.example.audiobook.enumPack.Enum_Roles;

public class Role {
    private Long id;

    private Enum_Roles name;


    public Role() {
    }

    public Role(Long id, Enum_Roles name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Enum_Roles getName() {
        return name;
    }

    public void setName(Enum_Roles name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}
