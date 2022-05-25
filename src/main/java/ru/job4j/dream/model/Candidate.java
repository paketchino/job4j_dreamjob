package ru.job4j.dream.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Candidate {

    private int id;
    private String name;
    private String desc;

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
        return id == candidate.id && visible == candidate.visible
                && Objects.equals(name, candidate.name)
                && Objects.equals(desc, candidate.desc) && Objects.equals(created, candidate.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, desc, visible, created);
    }
}
