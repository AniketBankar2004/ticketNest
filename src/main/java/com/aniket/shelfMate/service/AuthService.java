package com.aniket.shelfMate.service;

import com.aniket.shelfMate.config.JwtService;
import com.aniket.shelfMate.dtos.LoginRequest;
import com.aniket.shelfMate.dtos.LoginResponse;
import com.aniket.shelfMate.dtos.RegisterRequest;
import com.aniket.shelfMate.exceptions.EmailAlreadyExistsException;
import com.aniket.shelfMate.exceptions.InvalidUserException;
import com.aniket.shelfMate.exceptions.UserNameAlreadyExistsException;
import com.aniket.shelfMate.model.User;
import com.aniket.shelfMate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public void register(RegisterRequest request) throws UserNameAlreadyExistsException {

        if (userRepository.existsByUserName(request.username())) {
            throw new UserNameAlreadyExistsException("username already exists");
        }

        if (userRepository.existsByEmail(request.email())) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        User user = new User();

        user.setUserName(request.username());
        user.setEmail(request.email());

        user.setPassword(
                passwordEncoder.encode(request.password())
        );

        userRepository.save(user);
    }

    public LoginResponse login(LoginRequest request) {

        User user = userRepository
                .findByUserName(request.username())
                .orElseThrow(() ->
                        new InvalidUserException("Invalid username or password")
                );

        if (!passwordEncoder.matches(
                request.password(),
                user.getPassword())) {

            throw new InvalidUserException("Invalid username or password");
        }

        String token = jwtService.generateToken(user);

        return new LoginResponse(
                token,
                user.getUserName()
        );
    }
}