package com.example.lab2.controllers;

import com.example.lab2.config.AppConfig;
import com.example.lab2.models.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class FilmController {
    @Autowired
    private AppConfig appConfig;

    @GetMapping
    public String home(Model model) {
        List<Film> films = appConfig.getServiceFactory()
                .getFilmService().getAllFilms();
        model.addAttribute("films", films);
        return "/admin/home";
    }

    @GetMapping("/films/add")
    public String getAddFilmPage() {
        return "/admin/addFilm";
    }

    @PostMapping("/films/add")
    public String addFilm(@RequestParam(value = "actors[]") String[] actors,
                          @RequestParam Map<String, Object> film) {
        appConfig.getServiceFactory().getFilmService().add(film, actors);
        return "redirect:/admin";
    }

    @PostMapping("/films/delete")
    public String deleteFilm(@RequestParam int id) {
        appConfig.getServiceFactory().getFilmService().delete(id);
        return "redirect:/admin";
    }

    @GetMapping("/films/change")
    public String getChangeFilmPage(@RequestParam int id, Model model) {
        Film film = appConfig.getServiceFactory()
                .getFilmService().getById(id);
        model.addAttribute("film", film);
        return "/admin/changeFilm";
    }

    @PostMapping("/films/change")
    public String changeFilm(@RequestParam(value = "actors[]") String[] actors,
                             @RequestParam Map<String, Object> film,
                             @RequestParam int id
    ) {
        appConfig.getServiceFactory().getFilmService().change(film, actors, id);
        return "redirect:/admin";
    }
}
