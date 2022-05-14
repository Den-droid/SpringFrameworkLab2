package com.example.lab2.repositories.interfaces;

import com.example.lab2.models.Film;

import java.util.List;

public interface FilmRepository {
    List<Film> getAll();

    Film getById(int id);

    void add(Film film);

    void change(Film film);

    void delete(int id);
}
