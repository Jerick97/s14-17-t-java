package com.nocountry.TeamScore.roles.repository;
import com.nocountry.TeamScore.roles.model.dto.RolesDTO;
import java.util.List;
// esta interfaz es para incteractuar con la base de datos.
public interface RoleRepository {
    RolesDTO findById(Long id);
    List<RolesDTO> findAll();
    RolesDTO save(RolesDTO roleDTO);
    RolesDTO update(RolesDTO roleDTO);
    void delete(Long id);
}
