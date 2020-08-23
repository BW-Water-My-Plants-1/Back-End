package com.dgbuild.watermyplants.services;


import com.dgbuild.watermyplants.models.User;

import java.util.List;

public interface UserService {
    void deleteAll();

    void delete(long id);

    User save(User newUser);

    List<User> findAll();

    User findById(long id);
}
