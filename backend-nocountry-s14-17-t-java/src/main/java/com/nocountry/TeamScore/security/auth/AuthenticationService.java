package com.nocountry.TeamScore.security.auth;

import com.nocountry.TeamScore.security.auth.dto.AuthenticationRequest;
import com.nocountry.TeamScore.security.auth.dto.AuthenticationResponse;
import com.nocountry.TeamScore.security.auth.dto.RegisterRequest;
import com.nocountry.TeamScore.security.config.JwtService;
import com.nocountry.TeamScore.security.user.Role;
import com.nocountry.TeamScore.security.user.User;
import com.nocountry.TeamScore.security.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .name(request.getName()) // falta adaptar el usuario al mio
                .surname(request.getSurname())
                .email(request.getEmail())
                .status(request.getStatus())
                .operador(request.getOperador())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
