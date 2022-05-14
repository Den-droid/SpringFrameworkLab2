package com.example.lab2.services.interfaces;

public interface ServiceFactory {
    UserService getUserService();
    MovieSessionService getMovieSessionService();
    FilmService getFilmService();
}
