package com.nocountry.TeamScore.groups.model;

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
    private User user_id;
    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group_id;
    private String role;
}

