package com.nocountry.TeamScore.groups.repository;

import com.nocountry.TeamScore.groups.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
}
