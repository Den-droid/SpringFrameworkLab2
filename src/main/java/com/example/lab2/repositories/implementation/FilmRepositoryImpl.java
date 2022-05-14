package com.example.lab2.repositories.implementation;

import com.example.lab2.models.Film;
import com.example.lab2.repositories.interfaces.FilmRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class FilmRepositoryImpl implements FilmRepository {
    private final List<Film> films = new ArrayList<>();

    private FilmRepositoryImpl() {

    }
    @Override
    public List<Film> getAll() {
        return films;
    }

    @Override
    public Film getById(int id) {
        return films.stream()
                .filter(x -> x.getId() == id)
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("No such film"));
    }

    @Override
    public void add(Film film) {
        this.films.add(film);
    }

    @Override
    public void change(Film film) {
        Film filmToChange = this.getById(film.getId());
        int index = this.films.indexOf(filmToChange);
        this.films.set(index, film);
    }

    @Override
    public void delete(int id) {
        Film film = this.getById(id);
        this.films.remove(film);
    }
}
