package com.example.lab2.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Film {
    private int id;
    private String director;

    private String name;
    private LocalDate releaseDate;
    private List<String> actors;
    private LocalTime duration;
    private String description;
    private String ageRestriction;

    public Film() {
    }

    public Film(int id, String name, LocalDate releaseDate, List<String> actors, LocalTime duration,
                String description, String director) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.actors = actors;
        this.duration = duration;
        this.description = description;
        this.director = director;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(String ageRestriction) {
        this.ageRestriction = ageRestriction;
    }
}
