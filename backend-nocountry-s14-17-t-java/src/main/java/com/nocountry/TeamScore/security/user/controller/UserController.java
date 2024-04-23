package com.nocountry.TeamScore.security.user.controller;

import com.nocountry.TeamScore.feedback.model.Feedback;
import com.nocountry.TeamScore.groups.model.Group;
import com.nocountry.TeamScore.groups.model.GroupByUser;
import com.nocountry.TeamScore.groups.model.dto.GroupsInUsersDTO;
import com.nocountry.TeamScore.groups.repository.GroupByUserRepository;
import com.nocountry.TeamScore.groups.service.GroupService;
import com.nocountry.TeamScore.security.config.JwtService;
import com.nocountry.TeamScore.security.user.model.User;
import com.nocountry.TeamScore.security.user.model.dto.UserDTO;
import com.nocountry.TeamScore.security.user.model.dto.UserUpdateRequest;
import com.nocountry.TeamScore.security.user.model.dto.UsersInGroup;
import com.nocountry.TeamScore.security.user.service.UserService;
import com.nocountry.TeamScore.security.user.util.ProgressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/users")
@Slf4j
@Tag(name = "Users", description = "Endpoints para el manejo de usuarios")
public class UserController {

    private final UserService userService;
    private final GroupService groupService;
    private final GroupByUserRepository groupByUserRepository; // considerar despues hacer un service para esta tabla.
    private final JwtService jwtService;
    private final ProgressService progressService;

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}/usuarios-sin-votar/{idGroup}")
    @Operation(summary = "Obtiene los usuarios sin votar", description = "Obtiene los usuarios que aún no han sido evaluados")
    public ResponseEntity<?> getUsersSinVotar(@PathVariable Long id, @PathVariable Long idGroup) {
        return ResponseEntity.ok(userService.getUsersSinVotar(id, idGroup));
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
    @PutMapping("/{id}") // Recordar que al cambiar pass se debe autenticar nuevamente
    public ResponseEntity<HttpStatus> update(@Valid @RequestBody UserUpdateRequest request, @PathVariable Long id) {
        // Deshabilito este endpoint xq al actualizar la contraseña no la esta cifrando tengo que revisar eso
        userService.update(request,id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @SecurityRequirement(name = "bearerAuth")
//    @PostMapping("/{id}/makeFeedForUser/{idUser}/inGroup/{idGroup}")
//    public ResponseEntity<?> makeFeed(@Valid @RequestBody Feedback feedback, @PathVariable Long id,@PathVariable Long idUser, @PathVariable Long idGroup) {
//
//        // TODO falataria ver que servicios deberia usar este controller para crear el feedback
//        return null;
//    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/profile/{email}") // compara el email de aqui
    @PreAuthorize("#email == authentication.principal.username or hasAuthority('ROLE_ADMIN')") // con el usuario autenticado
    public ResponseEntity<?> getUserProfile(@PathVariable String email) {
        // aca iria la logica para traer el UserDTO que necesito consruir
        User user = userService.findByUsername(email);

        if (user == null) {
            log.warn("No se encontró el usuario: {}", email);
        } else {
            log.info("Usuario encontrado: {}", user);
        }
        List<Group> groups = groupService.getGroupsByUserEmail(email);
        List<GroupByUser> groupsByUser = groupService.findByUser_Email(email);
        List<GroupsInUsersDTO> misGrupos = groupsByUser.stream()
                .filter(gbu -> groups.contains(gbu.getGroup()))
                .map(gbu -> GroupsInUsersDTO.fromGroupAndGroupByUser(gbu.getGroup(), gbu))
                .collect(Collectors.toList());

        var userdto = UserDTO.builder()
                            .id(user.getId())
                            .name(user.getName())
                            .surname(user.getSurname())
                            .email(email)
                            .status(user.getStatus())
                            .operador(user.getOperador())
                            .groups(misGrupos)
                            .build();

        log.info("Perfil construido para el usuario: {}", userdto);

        return ResponseEntity.ok(userdto);
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/count/{status}")
    public ResponseEntity<Long> countByStatus(@PathVariable String status) {
        long count = userService.countByStatus(status);
        return ResponseEntity.ok(count);
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/usersInGroup/{idGroup}")
    public ResponseEntity<?> getUsersInGroup(@PathVariable Long idGroup) {
        List<GroupByUser> asignacionesGrupos = groupByUserRepository.findByGroupId(idGroup);
        List<UsersInGroup> usuarios = asignacionesGrupos.stream()
                .map(groupByUser -> UsersInGroup.fromGroupByUser(groupByUser, progressService))
                .collect(Collectors.toList());
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/me")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<User> getCurrentUser() {
        // Obtiene el UserDetails del contexto de seguridad
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Busca al usuario en la base de datos
        User user = userService.findByUsername(userDetails.getUsername());

        // Devuelve los datos del usuario
        return ResponseEntity.ok(user);
    }

}
