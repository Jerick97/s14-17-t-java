package com.nocountry.TeamScore.groups.repository;

import com.nocountry.TeamScore.groups.model.GroupByUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupByUserRepository extends JpaRepository<GroupByUser, Long> {
}
