package com.aniket.ticketNest.service;
import com.aniket.ticketNest.dtos.ShowRequest;
import com.aniket.ticketNest.dtos.ShowResponse;
import com.aniket.ticketNest.exceptions.MovieNotFoundException;
import com.aniket.ticketNest.exceptions.ShowNotFoundException;
import com.aniket.ticketNest.model.Movie;
import com.aniket.ticketNest.model.Show;
import com.aniket.ticketNest.repository.MovieRepository;
import com.aniket.ticketNest.repository.ShowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowService {

    private final MovieRepository movieRepository;
    private final ShowRepository showRepository;

    public ShowResponse getShowById(String id) {
        Show show = showRepository.findById(id)
                .orElseThrow(()->new ShowNotFoundException("Show not found: "+id));

        return show.toShowResponse();
    }


    public List<ShowResponse> getShowsByMovie(String movieId) {

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(()->new MovieNotFoundException("Movie not found: "+movieId));

        return showRepository.findByMovieId(movieId)
                .stream()
                .map(Show::toShowResponse)
                .toList();
    }

    public ShowResponse createShow(ShowRequest request) {

        Movie movie = movieRepository.findById(request.movieId())
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        Show show = new Show();

        show.setMovie(movie);
        show.setShowDate(request.showDate());
        show.setStartTime(request.startTime());

        Show savedShow = showRepository.save(show);

        return savedShow.toShowResponse();
    }

    public ShowResponse updateShow(String id, ShowRequest request) {

        Show show = showRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Show not found"));

        Movie movie = movieRepository.findById(request.movieId())
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        show.setMovie(movie);
        show.setShowDate(request.showDate());
        show.setStartTime(request.startTime());

        Show updatedShow = showRepository.save(show);

        return updatedShow.toShowResponse();
    }

    public void deleteShow(String id) {
        Show show = showRepository.findById(id)
                .orElseThrow(()->new ShowNotFoundException("Show not found: "+id));

        showRepository.deleteById(id);
    }
}
