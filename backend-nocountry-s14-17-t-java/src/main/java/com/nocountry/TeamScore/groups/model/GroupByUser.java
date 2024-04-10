package com.nocountry.TeamScore.groups.model;

import com.nocountry.TeamScore.security.user.model.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
@Entity
@Table(name = "group_by_user")
public class GroupByUser {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private User user;
    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;
    private String rolElegido;
    private Long role_id; // por ahora trabaja con Long, despues adaptar con la entidad Role
}

