package com.example.lab2.repositories.implementation;

import com.example.lab2.models.MovieSession;
import com.example.lab2.repositories.interfaces.MovieSessionRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Repository
public class MovieSessionRepositoryImpl implements MovieSessionRepository {
    private final List<MovieSession> movieSessions = new ArrayList<>();

    public MovieSessionRepositoryImpl() {
    }

    @Override
    public List<MovieSession> getByDate(LocalDate sessionDate) {
        return this.movieSessions.stream()
                .filter(x -> x.getSessionTime().toLocalDate().isEqual(sessionDate))
                .collect(Collectors.toList());
    }

    @Override
    public List<MovieSession> getAllByFilmId(int filmId) {
        return this.movieSessions.stream()
                .filter(x -> x.getFilm().getId() == filmId)
                .collect(Collectors.toList());
    }

    @Override
    public MovieSession getById(int id) {
        return this.movieSessions.stream()
                .filter(x -> x.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No such element"));
    }

    @Override
    public void add(MovieSession movieSession) {
        this.movieSessions.add(movieSession);
    }

    @Override
    public void change(MovieSession movieSession) {
        MovieSession movieSessionToChange = this.getById(movieSession.getId());
        int index = this.movieSessions.indexOf(movieSessionToChange);
        this.movieSessions.set(index, movieSession);
    }

    @Override
    public void delete(int id) {
        MovieSession movieSession = this.getById(id);
        this.movieSessions.remove(movieSession);
    }
}
