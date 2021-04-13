package org.liftoff.uTrain.data;

import org.liftoff.uTrain.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);

}
