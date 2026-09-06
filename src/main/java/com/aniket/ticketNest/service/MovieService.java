package com.aniket.ticketNest.service;

import com.aniket.ticketNest.dtos.MovieRequest;
import com.aniket.ticketNest.dtos.MovieResponse;
import com.aniket.ticketNest.exceptions.MovieNotFoundException;
import com.aniket.ticketNest.model.Movie;
import com.aniket.ticketNest.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    public List<MovieResponse> getMovies() {
        return movieRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();

    }

    private MovieResponse toResponse(Movie movie) {
        return new MovieResponse(
                String.valueOf(movie.getId()),
                movie.getTitle(),
                movie.getDescription(),
                movie.getDuration(),
                movie.getGenre(),
                movie.getLanguage(),
                movie.getReleaseDate()
        );
    }

    public MovieResponse getMovieById(String id) {
        Movie movie =  movieRepository.findById(id)
                .orElseThrow(()->new MovieNotFoundException("movie not found: "+id));

        return toResponse(movie);
    }

    public MovieResponse deleteMovie(String id) {
        Movie movie =  movieRepository.findById(id)
                .orElseThrow(()->new MovieNotFoundException("movie not found: "+id));

        movieRepository.deleteById(id);
        return toResponse(movie);
    }

    public MovieResponse createMovie(MovieRequest request) {
        Movie movie = new Movie();

        movie.setTitle(request.title());
        movie.setDescription(request.description());
        movie.setDuration(request.duration());
        movie.setGenre(request.genre());
        movie.setLanguage(request.language());
        movie.setReleaseDate(request.releaseDate());

        Movie savedMovie = movieRepository.save(movie);

        return toResponse(savedMovie);
    }

    public MovieResponse updateMovie(String id, MovieRequest request) {

        Movie movie =  movieRepository.findById(id)
                .orElseThrow(()->new MovieNotFoundException("movie not found: "+id));


        if (request.title() != null) {
            movie.setTitle(request.title());
        }

        if (request.description() != null) {
            movie.setDescription(request.description());
        }

        if (request.duration() != null) {
            movie.setDuration(request.duration());
        }

        if (request.genre() != null) {
            movie.setGenre(request.genre());
        }

        if (request.language() != null) {
            movie.setLanguage(request.language());
        }

        if (request.releaseDate() != null) {
            movie.setReleaseDate(request.releaseDate());
        }

        Movie savedMovie =  movieRepository.save(movie);
        return toResponse(savedMovie);
    }
}
