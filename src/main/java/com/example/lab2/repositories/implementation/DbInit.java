package com.example.lab2.repositories.implementation;

import com.example.lab2.models.Film;
import com.example.lab2.models.MovieSession;
import com.example.lab2.models.Seat;
import com.example.lab2.repositories.interfaces.FilmRepository;
import com.example.lab2.repositories.interfaces.MovieSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Component
public class DbInit {
    @Autowired
    private MovieSessionRepository movieSessionRepository;
    @Autowired
    private FilmRepository filmRepository;

    @PostConstruct
    private void setDB(){
        setFilms();
        setMovieSessions();
    }

    private void setFilms() {
        List<String> actors = new ArrayList<>();
        actors.add("New actor");
        actors.add("New actor2");
        actors.add("New actor3");
        Film film = new Film(1, "Filmname", LocalDate.of(2003, 10, 21), actors,
                LocalTime.of(2, 5, 56), "Description of film",
                "Director of film");
        List<String> actors2 = new ArrayList<>(actors);
        Film film2 = new Film(2, "New Filmname", LocalDate.of(2010, 9, 14), actors2,
                LocalTime.of(2, 10, 45), "Description of film",
                "Director of film");
        filmRepository.add(film);
        filmRepository.add(film2);
    }

    private void setMovieSessions() {
        Map<Integer, Seat[]> seats = new HashMap<>();
        Random rand = new Random();
        for (int i = 1; i < 11; i++) {
            Seat[] row = new Seat[20];
            for (int j = 1; j < 21; j++) {
                row[j - 1] = new Seat(j, rand.nextBoolean(), 15.99f);
            }
            seats.put(i, row);
        }
        Map<Integer, Seat[]> seats2 = new HashMap<>(seats);
        Map<Integer, Seat[]> seats3 = new HashMap<>(seats);
        movieSessionRepository.add(new MovieSession(1, this.filmRepository.getById(1),
                LocalDateTime.of(LocalDate.now().plusDays(1),
                        LocalTime.of(12, 50)), seats));
        movieSessionRepository.add(new MovieSession(2, this.filmRepository.getById(2),
                LocalDateTime.of(LocalDate.now().plusDays(2),
                        LocalTime.of(14, 45)), seats2));
        movieSessionRepository.add(new MovieSession(3, this.filmRepository.getById(2),
                LocalDateTime.of(LocalDate.now().plusDays(3),
                        LocalTime.of(16, 55)), seats3));
    }
}