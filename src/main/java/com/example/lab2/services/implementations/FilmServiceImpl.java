package com.example.lab2.services.implementations;

import com.example.lab2.models.Film;
import com.example.lab2.repositories.interfaces.FilmRepository;
import com.example.lab2.services.interfaces.FilmService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FilmServiceImpl implements FilmService {
    FilmRepository repository;

    public FilmServiceImpl(FilmRepository repository){
        this.repository = repository;
    }

    @Override
    public List<Film> getAllFilms() {
        return repository.getAll();
    }

    @Override
    public Film getById(int id) {
        return this.repository.getById(id);
    }

    @Override
    public void add(Map<String, Object> filmMap, String[] actors) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();

        List<String> actorsList = Arrays.stream(actors).collect(Collectors.toList());
        filmMap.remove("actors[]");
        filmMap.put("actors", actorsList);

        final Film filmToAdd = objectMapper.convertValue(filmMap, Film.class);
        this.repository.add(filmToAdd);
    }

    @Override
    public void change(Map<String, Object> filmMap, String[] actors, int id) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();

        List<String> actorsList = Arrays.stream(actors).collect(Collectors.toList());
        filmMap.remove("actors[]");
        filmMap.put("actors", actorsList);
        filmMap.put("id", id);

        final Film filmToChange = objectMapper.convertValue(filmMap, Film.class);
        this.repository.change(filmToChange);
    }

    @Override
    public void delete(int id) {
        this.repository.delete(id);
    }
}
