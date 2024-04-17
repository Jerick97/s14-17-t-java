package com.nocountry.TeamScore.security.user.repository;

import com.nocountry.TeamScore.security.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email); // me interesa buscar mis usuarios por el email que es el username

    long countByStatus(String status); // para recuperar usuarios participantes; status = "P"

    // deberia de agregar un metodo para contar los que estan en un grupo, pero quizas en group
}
