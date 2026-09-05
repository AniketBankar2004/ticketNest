package com.aniket.ticketNest.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(
        @NotBlank(message = "Username cannot be blank")
        String username,

        @Email(message = "Please enter a valid email")
        @NotBlank(message = "Please enter a email id")
                String email,

        @NotBlank(message = "please enter a password")
                String password
) {

}
