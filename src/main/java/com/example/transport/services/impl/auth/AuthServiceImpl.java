package com.example.transport.services.impl.auth;

import com.example.transport.config.JwtService;
import com.example.transport.exceptions.NotFoundException;
import com.example.transport.models.*;
import com.example.transport.models.auth.AuthenticationRequest;
import com.example.transport.models.auth.AuthenticationResponse;
import com.example.transport.repositories.UserRepository;
import com.example.transport.services.auth.AuthService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .login(request.getLogin())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getLogin(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByLogin(request.getLogin())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public String getLastPage(String login) {
        Optional<User> optionalUser = userRepository.findByLogin(login);
        User user = null;
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        } else {
            throw new NotFoundException("User not found. Login: " + login);
        }
        return user.getLastPage();
    }

    @Override
    public String setLastPage(String login, String lastPage) {
        Optional<User> optionalUser = userRepository.findByLogin(login);
        User user = null;
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        } else {
            throw new NotFoundException("User not found. Login: " + login);
        }
        user.setLastPage(lastPage);
        userRepository.save(user);
        return lastPage;
    }
}
