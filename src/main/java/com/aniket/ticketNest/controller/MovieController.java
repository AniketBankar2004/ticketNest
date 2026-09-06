package com.aniket.ticketNest.controller;
import com.aniket.ticketNest.dtos.MovieRequest;
import com.aniket.ticketNest.dtos.MovieResponse;
import com.aniket.ticketNest.repository.MovieRepository;
import com.aniket.ticketNest.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    private final MovieService movieService;
    private final MovieRepository movieRepository;

    @GetMapping
    public ResponseEntity<List<MovieResponse>> getMovies(){
        return ResponseEntity.ok(movieService.getMovies());
    }

    @DeleteMapping
    public ResponseEntity<MovieResponse> deleteMovie(
            @PathVariable String id
    ){
        return ResponseEntity.ok(movieService.deleteMovie(id));
    }

    @PostMapping
    public ResponseEntity<MovieResponse> createMovie(
            @RequestBody MovieRequest request
            ){
        return ResponseEntity.status(HttpStatus.CREATED).body(movieService.createMovie(request));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MovieResponse> updateMovie(
            @PathVariable String id,
            @RequestBody MovieRequest request) {

        return ResponseEntity.ok(movieService.updateMovie(id, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> getMovieById(
            @PathVariable String id
    ){
        return ResponseEntity.ok(movieService.getMovieById(id));
    }
}
