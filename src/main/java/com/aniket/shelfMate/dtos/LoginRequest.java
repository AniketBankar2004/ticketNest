package com.aniket.shelfMate.dtos;


import jakarta.validation.constraints.NotBlank;

public record LoginRequest(

        @NotBlank(message = "please enter a username")
        String username,

        @NotBlank(message = "please enter a password")
        String password
) {
}
