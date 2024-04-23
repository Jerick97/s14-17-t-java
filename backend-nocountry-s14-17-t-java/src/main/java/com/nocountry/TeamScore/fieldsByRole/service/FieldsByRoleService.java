package com.nocountry.TeamScore.fieldsByRole.service;



import com.nocountry.TeamScore.fieldsByRole.model.FieldsByRole;

import java.util.Set;

public interface FieldsByRoleService {
    void createFieldsByRole(FieldsByRole fieldsByRole);
    FieldsByRole getFieldsByRoleId(Long id);
    Set<FieldsByRole> getAllFieldsByRole();
    void updateFieldsByRole(FieldsByRole fieldsByRole);
    void deleteFieldsByRole(Long id);
}
