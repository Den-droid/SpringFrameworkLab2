package com.example.lab2.controllers;

import com.example.lab2.config.AppConfig;
import com.example.lab2.models.Film;
import com.example.lab2.models.MovieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping(name = "/")
public class UserController {
    @Autowired
    private AppConfig appConfig;

    @GetMapping
    public String home(Model model) {
        LocalDateTime dateTime = appConfig.getCurrentDateTime();
        List<Film> films = appConfig.getServiceFactory()
                .getMovieSessionService()
                .getFilmsBySessionDate(dateTime.toLocalDate());
        model.addAttribute("films", films);
        model.addAttribute("todayDate", dateTime.toLocalDate());
        return "home";
    }

    @GetMapping("/search")
    public String searchFilms(@RequestParam(name = "film", required = false) String film,
                              @RequestParam(name = "date") String date,
                              Model model) {
        List<Film> films;
        LocalDate localDate = LocalDate.parse(date);
        if (!film.isEmpty()) {
            films = appConfig.getServiceFactory()
                    .getMovieSessionService()
                    .getFilmsBySessionDateAndFilmName(localDate, film);
            model.addAttribute("filmName", film);
        } else {
            films = appConfig.getServiceFactory()
                    .getMovieSessionService()
                    .getFilmsBySessionDate(localDate);
        }
        model.addAttribute("films", films);
        model.addAttribute("todayDate", localDate);
        return "home";
    }

    @GetMapping("/films/schedule")
    public String filmSchedule(@RequestParam(name = "id") int filmId,
                               Model model) {
        LocalDateTime dateTime = appConfig.getCurrentDateTime();
        LocalDate date = dateTime.toLocalDate();
        List<MovieSession> movieSessions = appConfig.getServiceFactory()
                .getMovieSessionService()
                .getAllByFilmIdAndBetweenDates(date, date.plusDays(7), filmId);
        boolean[] isFreeMovieSessions = this.appConfig.getServiceFactory()
                .getMovieSessionService()
                .isFreeSeatsMovieSessions(movieSessions);
        model.addAttribute("isFreeSeats", isFreeMovieSessions);
        model.addAttribute("film", movieSessions.get(0).getFilm());
        model.addAttribute("movieSessions", movieSessions);
        return "timetable";
    }

    @PostMapping("/films/schedule")
    public String bookTicket(@RequestParam int movieSessionId,
                             @RequestParam int row,
                             @RequestParam int seat) {
        appConfig.getServiceFactory()
                .getMovieSessionService()
                .bookTicket(movieSessionId, row, seat);
        return "redirect:/";
    }
}
