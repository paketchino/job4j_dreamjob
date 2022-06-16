package ru.job4j.dreamjob.model;

import java.util.Objects;

public class User {

    private int id;

    private String name;

    private String email;

    private String password;

    private City city;

    public User() {
    }

    public User(int id, String name, String email, String password, City city) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.city = city;
    }

    public User(int id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public User(int id, String email, String password, City city) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
