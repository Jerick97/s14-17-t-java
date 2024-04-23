package com.nocountry.TeamScore.fieldsByRole.service;


import com.nocountry.TeamScore.fieldsByRole.model.FieldsByRole;
import com.nocountry.TeamScore.fieldsByRole.repository.FieldsByRoleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class FieldsByRoleServiceImpl implements FieldsByRoleService{
    @Autowired
    private FieldsByRoleRepository fieldsByRoleRepository;
    @Override
    public void createFieldsByRole(FieldsByRole fieldsByRole) {
        fieldsByRoleRepository.save(fieldsByRole);
    }

    @Override
    public FieldsByRole getFieldsByRoleId(Long id) {
        Optional<FieldsByRole> fieldsByRole = fieldsByRoleRepository.findById(id);

        if (fieldsByRole.isPresent()){
            return fieldsByRole.get();
        }else {
            throw new EntityNotFoundException("El campo por rol con el id: " + id + " no existe");
        }

    }

    @Override
    public Set<FieldsByRole> getAllFieldsByRole() {
        List<FieldsByRole> fieldsByRoles = fieldsByRoleRepository.findAll();
        return Set.copyOf(fieldsByRoles);
    }

    @Override
    public void updateFieldsByRole(FieldsByRole fieldsByRole) {
        fieldsByRoleRepository.save(fieldsByRole);
    }

    @Override
    public void deleteFieldsByRole(Long id) {
        fieldsByRoleRepository.deleteById(id);
    }
}
