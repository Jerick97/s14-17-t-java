package com.nocountry.TeamScore.security.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nocountry.TeamScore.groups.model.GroupByUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})}) // agrego restriccion de email unicos
public class User implements UserDetails {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String status; // considerar que el status sea un booleano, y ver si no es lo mismo que el enabled de spring-boot
    private Boolean isEnabled;
    private Integer operador; // esto seria el rol del usuario que maneja springboot
    private String password;
    @OneToMany(mappedBy = "user_id")
    @JsonIgnore
    private Set<GroupByUser> groupByUserSet;

    @Enumerated(EnumType.STRING)
    private Role role; // esto es role para manejar seguridad no el rol del desarrollo en el proyecto

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name())); // genero los permisos por roles.
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email; // aqui determino que el username es el email, q es como lo gestiona NC
    }

    // todos los siguientes atributos los voy a manejar por oauth
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
