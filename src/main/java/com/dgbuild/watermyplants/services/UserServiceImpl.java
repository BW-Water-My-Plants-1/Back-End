package com.dgbuild.watermyplants.services;

import com.dgbuild.watermyplants.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "userService")
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }
}
