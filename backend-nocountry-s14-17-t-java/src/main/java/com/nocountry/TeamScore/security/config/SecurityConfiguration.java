package com.nocountry.TeamScore.security.config;

import com.nocountry.TeamScore.security.user.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(authRequest ->
                        authRequest
                                .requestMatchers( "/auth/**","/v3/api-docs/**","/swagger-ui.html", "/swagger-ui/**", "/api-docs.yaml","/swagger-ui-custom.html").permitAll() // para poder entrar a estos endpoints sin autenticar
                                .requestMatchers(HttpMethod.POST,"/api/v1/auth/register").permitAll()
                                .requestMatchers(HttpMethod.POST,"/api/v1/auth/authenticate").permitAll()


                )
                .authorizeHttpRequests(authRequest ->
                        authRequest
                                .requestMatchers(HttpMethod.GET,"/api/v1/groups/**").hasAnyAuthority(Role.ROLE_ADMIN.toString(), Role.ROLE_TL.toString())// esto despues hay que ponerlo para que solo los admin puedan crear grupos.
                                .requestMatchers(HttpMethod.POST,"/api/v1/groups/**").hasAnyAuthority(Role.ROLE_ADMIN.toString(), Role.ROLE_TL.toString())
                                .requestMatchers("/ejemplos/privado").authenticated()
                )
                .authorizeHttpRequests(authRequest ->
                        authRequest
                                .requestMatchers(HttpMethod.POST).hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE).hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET).hasAnyRole("ADMIN","TL")
                                .requestMatchers(HttpMethod.PUT).hasRole("ADMIN")
                )
                .authorizeHttpRequests(
                        authRequest -> authRequest.anyRequest().permitAll()
                )
                .sessionManagement(sessionManager ->
                        sessionManager
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // ApiRest
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); // le agrego mi filtro de seguridad

        return http.build();
    }
}
