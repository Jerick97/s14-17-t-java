package com.nocountry.TeamScore.security.user.controller;

import com.nocountry.TeamScore.feedback.model.Feedback;
import com.nocountry.TeamScore.security.user.model.User;
import com.nocountry.TeamScore.security.user.model.dto.UserUpdateRequest;
import com.nocountry.TeamScore.security.user.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/sinVotar")
    public ResponseEntity<?> getUsersSinVotar() {
        return ResponseEntity.ok("Aca iría una lista de usuarios sin votar, por proyecto?o en total por simulacion?");
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.getByID(id));
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/username/{username}")
    public ResponseEntity<User> getByUsername(@PathVariable String username)  {
        return ResponseEntity.ok().body(userService.findByUsername(username));
    }

    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@Valid @RequestBody UserUpdateRequest request, @PathVariable Long id) {
        userService.update(request,id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/{id}/makeFeedForUser/{idUser}/inGroup/{idGroup}")
    public ResponseEntity<?> makeFeed(@Valid @RequestBody Feedback feedback, @PathVariable Long id,@PathVariable Long idUser, @PathVariable Long idGroup) {

        // TODO falataria ver que servicios deberia usar este controller para crear el feedback
        return null;
    }
}
