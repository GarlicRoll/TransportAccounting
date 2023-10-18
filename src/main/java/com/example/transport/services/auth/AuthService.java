package com.example.transport.services.auth;

import com.example.transport.models.RegisterRequest;
import com.example.transport.models.auth.AuthenticationRequest;
import com.example.transport.models.auth.AuthenticationResponse;

public interface AuthService {

    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);

    String getLastPage(String login);

    String setLastPage(String login, String lastPage);
}
