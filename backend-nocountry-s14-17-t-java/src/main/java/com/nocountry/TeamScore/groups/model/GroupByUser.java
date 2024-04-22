package com.nocountry.TeamScore.groups.model;


import com.nocountry.TeamScore.roles.model.Roles;
import com.nocountry.TeamScore.security.user.model.User;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    @ManyToOne
    private Roles role_id;
}

