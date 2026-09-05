package com.aniket.shelfMate.dtos;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


public record LoginResponse(
        String token,
        String username
) {
}
