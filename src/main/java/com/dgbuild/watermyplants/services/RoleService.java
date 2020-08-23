package com.dgbuild.watermyplants.services;

import com.dgbuild.watermyplants.models.Role;

import java.util.List;

public interface RoleService {
    void deleteAll();

    void delete(long id);

    List<Role> findAll();

    Role findById(long id);

    Role save(Role newRole);
}
