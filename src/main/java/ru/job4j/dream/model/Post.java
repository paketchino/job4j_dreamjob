package ru.job4j.dream.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Post implements Serializable {

    private int id;
    private String name;

    private String desc;

    private boolean visible;

    private LocalDateTime created;

    public Post() {
    }

    public Post(int id, String name, String desc, boolean visible, LocalDateTime created) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.visible = visible;
        this.created = created;
    }

    public int getId() {
        return id;
    }

    public int setId(int id) {
        this.id = id;
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id == post.id && visible == post.visible && Objects.equals(name, post.name) && Objects.equals(desc, post.desc) && Objects.equals(created, post.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, desc, visible, created);
    }
}
