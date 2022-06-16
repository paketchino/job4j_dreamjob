package ru.job4j.dreamjob.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Candidate implements Serializable {

    private int id;
    private String name;
    private String desc;

    private byte[] photo;
    private boolean visible;
    private LocalDateTime created;

    public Candidate() {

    }

    public Candidate(int id, String name, String desc, boolean visible, LocalDateTime created) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.visible = visible;
        this.created = created;
    }

    public Candidate(int id, String name, String desc,
                     byte[] photo, boolean visible, LocalDateTime created) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.photo = photo;
        this.visible = visible;
        this.created = created;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
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

    public LocalDateTime getTimeFormat() {
        return created;
    }

    public void setTimeFormat(LocalDateTime created) {
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
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Candidate candidate = (Candidate) o;
        return id == candidate.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
