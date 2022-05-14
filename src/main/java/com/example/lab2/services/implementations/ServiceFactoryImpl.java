package com.example.lab2.services.implementations;

import com.example.lab2.services.interfaces.FilmService;
import com.example.lab2.services.interfaces.MovieSessionService;
import com.example.lab2.services.interfaces.ServiceFactory;
import com.example.lab2.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceFactoryImpl implements ServiceFactory {
    private UserService userService;
    private MovieSessionService movieSessionService;
    private FilmService filmService;
    @Override
    public UserService getUserService() {
        return this.userService;
    }
    @Override
    public MovieSessionService getMovieSessionService() {
        return this.movieSessionService;
    }
    @Override
    public FilmService getFilmService() {
        return this.filmService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setMovieSessionService(MovieSessionService movieSessionService) {
        this.movieSessionService = movieSessionService;
    }

    @Autowired
    public void setFilmService(FilmService filmService) {
        this.filmService = filmService;
    }
}
