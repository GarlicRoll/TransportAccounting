package com.example.transport.controllers.auth;

import com.example.transport.models.auth.AuthenticationRequest;
import com.example.transport.models.auth.AuthenticationResponse;
import com.example.transport.models.RegisterRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import com.example.transport.services.auth.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
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
            @RequestBody RegisterRequest request,
            @CookieValue(value = "last_page", defaultValue = "") String lastPage
    ) {
        System.out.println("Last page: " + lastPage);
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request,
            HttpServletResponse response
    ) {
        String lastPage = authService.getLastPage(request.getLogin());

        Cookie cookie = new Cookie("last_page", lastPage);

        cookie.setSecure(true); // if you want the cookie to be sent only over HTTPS
        cookie.setPath("/"); // add this if cookie is not getting set
        response.addCookie(cookie);

        AuthenticationResponse authenticationResponse = authService.authenticate(request);

        return ResponseEntity.ok().body(authenticationResponse);
    }

    @PostMapping("/logOut")
    public ResponseEntity<Void>  logOut(
            @RequestBody AuthenticationRequest request,
            @CookieValue(value = "last_page", defaultValue = "") String lastPage,
            HttpServletRequest httpRequest
    ) {
        Cookie[] cookies = httpRequest.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("last_page") && !cookie.getValue().isEmpty()) {
                    lastPage = cookie.getValue();
                }
                System.out.println("Cookie name: " + cookie.getName());
                System.out.println("Cookie value: " + cookie.getValue());
            }
        }
        System.out.println("Last page: " + lastPage);
        authService.setLastPage(request.getLogin(), lastPage);

        return ResponseEntity.ok().build();
    }
}
