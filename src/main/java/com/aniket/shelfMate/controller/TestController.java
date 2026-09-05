package com.aniket.shelfMate.controller;

import com.aniket.shelfMate.config.JwtService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/test")
@RestController
@RequiredArgsConstructor
public class TestController {

    private final JwtService jwtService;

    @GetMapping
    public String greet(@RequestHeader("Authorization") String authHeader) {

        String token = authHeader.substring(7);

        String username = jwtService.extractUsername(token);

        return "Hello: " + username;
    }

}
