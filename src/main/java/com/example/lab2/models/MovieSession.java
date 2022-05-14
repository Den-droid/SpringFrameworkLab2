package com.example.lab2.models;

import java.time.LocalDateTime;
import java.util.Map;

public class MovieSession {
    private int id;
    private Film film;
    private LocalDateTime sessionTime;
    private Map<Integer, Seat[]> seats;

    public MovieSession() {
    }

    public MovieSession(int id, Film film, LocalDateTime sessionTime, Map<Integer, Seat[]> seats) {
        this.id = id;
        this.film = film;
        this.sessionTime = sessionTime;
        this.seats = seats;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public LocalDateTime getSessionTime() {
        return sessionTime;
    }

    public void setSeats(Map<Integer, Seat[]> seats) {
        this.seats = seats;
    }

    public void setSessionTime(LocalDateTime sessionTime) {
        this.sessionTime = sessionTime;
    }

    public int getId() {
        return id;
    }

    public Map<Integer, Seat[]> getSeats() {
        return seats;
    }
}
