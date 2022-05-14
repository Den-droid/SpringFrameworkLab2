package com.example.lab2.services.implementations;

import com.example.lab2.models.Film;
import com.example.lab2.models.MovieSession;
import com.example.lab2.models.Seat;
import com.example.lab2.repositories.interfaces.FilmRepository;
import com.example.lab2.repositories.interfaces.MovieSessionRepository;
import com.example.lab2.services.interfaces.MovieSessionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MovieSessionServiceImpl implements MovieSessionService {
    private final MovieSessionRepository repository;
    private final FilmRepository filmRepository;

    public MovieSessionServiceImpl(MovieSessionRepository repository, FilmRepository filmRepository) {
        this.repository = repository;
        this.filmRepository = filmRepository;
    }

    @Override
    public List<Film> getFilmsBySessionDate(LocalDate localDate) {
        return this.repository.getByDate(localDate).stream()
                .map(MovieSession::getFilm)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<Film> getFilmsBySessionDateAndFilmName(LocalDate localDate, String filmName) {
        List<Film> filmList = getFilmsBySessionDate(localDate);
        return filmList.stream()
                .filter(x -> x.getName().toLowerCase().contains(filmName.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<MovieSession> getAllByFilmIdAndBetweenDates(LocalDate firstDate, LocalDate lastDate, int filmId) {
        LocalDateTime firstDateTime = firstDate.atStartOfDay();
        LocalDateTime lastDateTime = lastDate.atStartOfDay();
        return this.repository.getAllByFilmId(filmId).stream()
                .filter(x -> x.getSessionTime().isAfter(firstDateTime) &&
                        x.getSessionTime().isBefore(lastDateTime.plusDays(1)))
                .collect(Collectors.toList());
    }

    @Override
    public List<MovieSession> getAllByFilmId(int filmId) {
        return this.repository.getAllByFilmId(filmId);
    }

    @Override
    public MovieSession getById(int id) {
        return this.repository.getById(id);
    }

    @Override
    public boolean[] isFreeSeatsMovieSessions(List<MovieSession> movieSessions) {
        boolean[] result = new boolean[movieSessions.size()];
        for (int i = 0; i < movieSessions.size(); i++) {
            boolean isFree = isFreeSeatsMovieSession(movieSessions.get(i));
            result[i] = isFree;
        }
        return result;
    }

    private boolean isFreeSeatsMovieSession(MovieSession movieSession) {
        return movieSession.getSeats().values().stream()
                .anyMatch(x -> Arrays.stream(x).anyMatch(y -> !y.isBooked()));
    }

    @Override
    public void add(Map<String, String> movieSession, int filmId) {
        int rowNumber = Integer.parseInt(movieSession.get("rowNumber"));
        int seatsNumber = Integer.parseInt(movieSession.get("seatsNumber"));
        float price = Float.parseFloat(movieSession.get("price"));
        LocalDateTime sessionTime = LocalDateTime.parse(movieSession.get("sessionTime"));

        MovieSession movieSessionToAdd = new MovieSession();
        HashMap<Integer, Seat[]> seats = new HashMap<>();
        for (int i = 0; i < rowNumber; i++) {
            Seat[] row = new Seat[seatsNumber];
            for (int j = 0; j < seatsNumber; j++) {
                row[j] = new Seat(j + 1, false, price);
            }
            seats.put(i + 1, row);
        }
        movieSessionToAdd.setSeats(seats);
        movieSessionToAdd.setSessionTime(sessionTime);
        movieSessionToAdd.setFilm(this.filmRepository.getById(filmId));

        this.repository.add(movieSessionToAdd);
    }

    @Override
    public void change(Map<String, String> movieSession, int movieSessionId) {
        MovieSession movieSession1 = this.getById(movieSessionId);

        LocalDateTime sessionTime = LocalDateTime.parse(movieSession.get("sessionTime"));
        int rowNumber = Integer.parseInt(movieSession.get("rowNumber"));
        int seatNumber = Integer.parseInt(movieSession.get("seatNumber"));
        if (movieSession.get("price") == null) {
            movieSession1.setSessionTime(sessionTime);
        } else {
            float price = Float.parseFloat(movieSession.get("price"));
            boolean isBooked = Boolean.parseBoolean(movieSession.get("isBooked"));
            Seat[] seats = movieSession1.getSeats().get(rowNumber);
            seats[seatNumber - 1].setPrice(price);
            seats[seatNumber - 1].setBooked(isBooked);
        }

        // this.repository.change(movieSession1);
    }

    @Override
    public void delete(int id) {
        this.repository.delete(id);
    }

    @Override
    public void bookTicket(int movieSessionId, int row, int seat) {
        MovieSession movieSession = this.repository.getById(movieSessionId);
        Seat[] seats = movieSession.getSeats().get(row);
        seats[seat - 1].setBooked(true);
    }
}