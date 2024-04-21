package com.nocountry.TeamScore.groups.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nocountry.TeamScore.projects.model.Project;
import com.nocountry.TeamScore.security.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "_groups", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class Group {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_project", referencedColumnName = "id")
    private Project projectId;
    private String name;
    private String description;
    private Integer status; // el status de un grupo es para saber si esta activo o no
    @OneToMany(mappedBy = "group")
    @JsonIgnore // para que el mapeo con un dto ignore esta propiedad
    private Set<GroupByUser> groupByUserSet;

    @JsonIgnore
    public Set<User> getUsers() {
        return groupByUserSet.stream()
                .map(GroupByUser::getUser)
                .collect(Collectors.toSet());
    }

    @JsonIgnore
    public Set<User> getUsersEnabled() {
        return groupByUserSet.stream()
                .map(GroupByUser::getUser)
                .filter(user -> !user.getStatus().equalsIgnoreCase("D"))
                .collect(Collectors.toSet());
    }
}
