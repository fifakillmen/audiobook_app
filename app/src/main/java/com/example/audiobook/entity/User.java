package com.example.audiobook.entity;



import com.example.audiobook.enumPack.Enum_Gender;

import java.time.LocalDate;
import java.util.List;

public class User {
    private Long id;

    private String name;
    private LocalDate dob;
    private String address;
    private String avatar;
    private Enum_Gender gender;
    private List<Book> favorites;
    public User() {
    }

    public User(Long id, String name, LocalDate dob, String address, String avatar, Enum_Gender gender, List<Book> favorites) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.address = address;
        this.avatar = avatar;
        this.gender = gender;
        this.favorites = favorites;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Enum_Gender getGender() {
        return gender;
    }

    public void setGender(Enum_Gender gender) {
        this.gender = gender;
    }

    public List<Book> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Book> favorites) {
        this.favorites = favorites;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                ", address='" + address + '\'' +
                ", avatar='" + avatar + '\'' +
                ", gender=" + gender +
                ", favorites=" + favorites +
                '}';
    }
}
