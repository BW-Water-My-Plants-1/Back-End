package com.dgbuild.watermyplants.repositories;

import com.dgbuild.watermyplants.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
