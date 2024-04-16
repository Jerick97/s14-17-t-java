package com.nocountry.TeamScore.security.auth;

import com.nocountry.TeamScore.groups.model.dto.GroupsInUsersDTO;
import com.nocountry.TeamScore.security.auth.dto.AuthenticationRequest;
import com.nocountry.TeamScore.security.auth.dto.AuthenticationResponse;
import com.nocountry.TeamScore.security.auth.dto.RegisterRequest;
import com.nocountry.TeamScore.security.config.JwtService;
import com.nocountry.TeamScore.security.user.model.Role;
import com.nocountry.TeamScore.security.user.model.User;
import com.nocountry.TeamScore.security.user.repository.UserRepository;
import com.nocountry.TeamScore.security.user.util.UserToDtoService;
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
    private final UserToDtoService userToDtoService;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .name(request.getName()) // falta adaptar el usuario al mio
                .surname(request.getSurname())
                .email(request.getEmail())
                .isEnabled(true) // por defecto todos los usuarios se crean habilitados, despues vamos a implementar lo de activacion por correo
                .status(request.getStatus())
                .operador(request.getOperador())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.valueOf(request.getRole()))  // esto puede disparar una ilegal argument si se pasa un rol incorrecto
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
        var user = userRepository.findByEmail(request.getEmail()) // aqui podría usar el service ahora pero tendría que crearle tmb un create para el caso de registro
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .id(String.valueOf(user.getId()))
                .name(String.valueOf(user.getName()))
                .surname(String.valueOf(user.getSurname()))
                .email(String.valueOf(user.getEmail()))
                .status(String.valueOf(user.getStatus()))
                .operador(String.valueOf(user.getOperador()))
                .groups(userToDtoService.createGroupsDtosFromUser(user))
                // tener en cuenta que estoy hardcodeando el id del role, y el rol hasta que este la relacion Role
                .build();
    }
}
