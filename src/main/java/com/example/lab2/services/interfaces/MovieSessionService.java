package com.example.lab2.services.interfaces;

import com.example.lab2.models.Film;
import com.example.lab2.models.MovieSession;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface MovieSessionService {
    List<Film> getFilmsBySessionDate(LocalDate localDate);

    List<Film> getFilmsBySessionDateAndFilmName(LocalDate localDate, String filmName);

    List<MovieSession> getAllByFilmIdAndBetweenDates(LocalDate firstDate, LocalDate lastDate, int filmId);

    List<MovieSession> getAllByFilmId(int filmId);

    MovieSession getById(int id);

    void bookTicket(int movieSessionId, int row, int seat);

    void add(Map<String, String> movieSession, int filmId);

    void change(Map<String, String> movieSession, int movieSessionId);

    void delete(int id);

    boolean[] isFreeSeatsMovieSessions(List<MovieSession> movieSessions);
}
