package com.aniket.ticketNest.controller;

import com.aniket.ticketNest.dtos.ShowRequest;
import com.aniket.ticketNest.dtos.ShowResponse;
import com.aniket.ticketNest.service.ShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@RestController
@RequestMapping("/api/shows")
@RequiredArgsConstructor
public class ShowController {

    private final ShowService showService;

    // GET /api/shows/{id}
    @GetMapping("/{id}")
    public ResponseEntity<ShowResponse> getShowById(
            @PathVariable String id) {

        return ResponseEntity.ok(showService.getShowById(id));
    }

    // GET /api/shows/movie/{movieId}
    // Alternative endpoint if you prefer keeping everything under /api/shows
    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<ShowResponse>> getShowsByMovie(
            @PathVariable String movieId) {

        return ResponseEntity.ok(showService.getShowsByMovie(movieId));
    }

    // POST /api/shows
    @PostMapping
    public ResponseEntity<ShowResponse> createShow(
            @RequestBody ShowRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(showService.createShow(request));
    }

    // PUT /api/shows/{id}
    @PutMapping("/{id}")
    public ResponseEntity<ShowResponse> updateShow(
            @PathVariable String id,
            @RequestBody ShowRequest request) {

        return ResponseEntity.ok(showService.updateShow(id, request));
    }

    // DELETE /api/shows/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShow(
            @PathVariable String id) {

        showService.deleteShow(id);
        return ResponseEntity.noContent().build();
    }
}