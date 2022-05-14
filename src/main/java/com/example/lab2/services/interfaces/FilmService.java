package com.example.lab2.services.interfaces;

import com.example.lab2.models.Film;

import java.util.List;
import java.util.Map;

public interface FilmService {
    List<Film> getAllFilms();

    Film getById(int id);

    void add(Map<String, Object> filmMap, String[] actors);

    void change(Map<String, Object> filmMap, String[] actors, int id);

    void delete(int id);
}
