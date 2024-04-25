package com.nocountry.TeamScore.groups.repository;

import com.nocountry.TeamScore.groups.model.GroupByUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupByUserRepository extends JpaRepository<GroupByUser, Long> {
    List<GroupByUser> findByUser_Email(String email);
    GroupByUser findByUserIdAndGroupId(Long userId, Long groupId);

    List<GroupByUser> findByGroupId(Long groupId);

}
