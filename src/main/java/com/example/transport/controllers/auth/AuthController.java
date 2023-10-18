package com.example.transport.controllers.auth;

import com.example.transport.models.auth.AuthenticationRequest;
import com.example.transport.models.auth.AuthenticationResponse;
import com.example.transport.models.RegisterRequest;
import com.example.transport.services.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request,
            @CookieValue(value = "last_page", defaultValue = "none") String lastPage
    ) {
        System.out.println(lastPage); // Using cookies

        return ResponseEntity.ok(authService.authenticate(request));
    }

}
