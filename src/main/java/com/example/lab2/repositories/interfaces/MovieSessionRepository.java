package com.example.lab2.repositories.interfaces;

import com.example.lab2.models.MovieSession;

import java.time.LocalDate;
import java.util.List;

public interface MovieSessionRepository {
    List<MovieSession> getByDate(LocalDate sessionDate);

    MovieSession getById(int id);

    List<MovieSession> getAllByFilmId(int id);

    void add(MovieSession movieSession);

    void change(MovieSession movieSession);

    void delete(int id);
}
