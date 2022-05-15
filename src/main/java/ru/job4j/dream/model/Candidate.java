package ru.job4j.dream.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

public class Candidate {

    private int id;
    private String name;
    private String desc;

    private LocalDate date = LocalDate.now();

    public Candidate() {

    }

    public Candidate(int id, String name, String desc, LocalDate date) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public LocalDate getTimeFormat() {
        return date;
    }

    public void setTimeFormat(LocalDate parsedDate) {
        this.date = parsedDate;
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
        return id == candidate.id && Objects.equals(name, candidate.name)
                && Objects.equals(desc, candidate.desc)
                && Objects.equals(date, candidate.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, desc, date);
    }
}
