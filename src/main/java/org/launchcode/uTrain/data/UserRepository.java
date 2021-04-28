package org.launchcode.uTrain.data;

import org.launchcode.uTrain.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);
}