package com.nocountry.TeamScore.roles.service;

import com.nocountry.TeamScore.roles.model.dto.RolesDTO;
import com.nocountry.TeamScore.roles.repository.RoleRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoleServiceImpl implements RoleRepository {
    // Simulación de la base de datos en memoria implementada
    private Map<Long, RolesDTO> roles = new HashMap<>();
    private Long idCounter = 1L;

    @Override
    public RolesDTO findById(Long id) {
        return roles.get(id);
    }

    @Override
    public List<RolesDTO> findAll() {
        return new ArrayList<>(roles.values());
    }

    @Override
    public RolesDTO save(RolesDTO roleDTO) {
        roleDTO.setId(idCounter);
        roles.put(idCounter, roleDTO);
        idCounter++;
        return roleDTO;
    }

    @Override
    public RolesDTO update(RolesDTO roleDTO) {
        roles.put(roleDTO.getId(), roleDTO);
        return roleDTO;
    }

    @Override
    public void delete(Long id) {
        roles.remove(id);
    }
}

