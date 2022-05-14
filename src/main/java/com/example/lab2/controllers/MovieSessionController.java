package com.example.lab2.controllers;

import com.example.lab2.config.AppConfig;
import com.example.lab2.models.Film;
import com.example.lab2.models.MovieSession;
import com.example.lab2.models.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MovieSessionController {
    @Autowired
    private AppConfig appConfig;

    @GetMapping("/admin/films/{id}/movieSessions/add")
    public String getAddMovieSessionPage(@PathVariable(name = "id") int filmId,
                                         Model model) {
        Film film = appConfig.getServiceFactory().getFilmService().getById(filmId);
        model.addAttribute("film", film);
        return "/admin/addMovieSession";
    }

    @PostMapping("/admin/films/{id}/movieSessions/add")
    public String addMovieSession(@PathVariable(name = "id") int filmId,
                                  @RequestParam HashMap<String, String> movieSession) {
        appConfig.getServiceFactory().getMovieSessionService().add(movieSession, filmId);
        return "redirect:/admin";
    }

    @GetMapping("/admin/films/{id}/movieSessions/delete")
    public String getDeleteMovieSessionPage(@PathVariable(name = "id") int filmId,
                                            Model model) {
        List<MovieSession> movieSessions = appConfig.getServiceFactory()
                .getMovieSessionService().getAllByFilmId(filmId);
        model.addAttribute("movieSessions", movieSessions);
        return "/admin/deleteMovieSession";
    }

    @PostMapping("/admin/films/{id}/movieSessions/delete")
    public String deleteMovieSession(@RequestParam int id) {
        appConfig.getServiceFactory().getMovieSessionService().delete(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/films/{id}/movieSessions/chooseToChange")
    public String getChangeMovieSessionPage(@PathVariable(name = "id") int filmId,
                                            Model model) {
        List<MovieSession> movieSessions = appConfig.getServiceFactory()
                .getMovieSessionService().getAllByFilmId(filmId);
        model.addAttribute("movieSessions", movieSessions);
        return "/admin/chooseMovieSessionToChange";
    }

    @GetMapping("/admin/films/{id}/movieSessions/change")
    public String changeMovieSession(@RequestParam int id, Model model) {
        MovieSession movieSession = appConfig.getServiceFactory()
                .getMovieSessionService()
                .getById(id);
        model.addAttribute("movieSession", movieSession);
        return "/admin/changeMovieSession";
    }

    @PostMapping("/admin/films/{id}/movieSessions/change")
    public String changeMovieSession(@RequestParam int id,
                                     @RequestParam HashMap<String, String> movieSession) {
        appConfig.getServiceFactory().getMovieSessionService().change(movieSession, id);
        return "redirect:/admin";
    }

    @GetMapping("/movieSessions/{id}/seats/{row}/{seat}")
    @ResponseBody
    public Map<String, Object> getSeatInfo(@PathVariable int id,
                                           @PathVariable int row,
                                           @PathVariable int seat){
        MovieSession movieSession = appConfig.getServiceFactory()
                .getMovieSessionService()
                .getById(id);
        Seat[] seats = movieSession.getSeats().get(row);
        Map<String, Object> map = new HashMap<>();
        map.put("isBooked", seats[seat - 1].isBooked());
        map.put("price", seats[seat - 1].getPrice());
        return map;
    }
}
