package org.launchcode.uTrain.data;

import org.launchcode.uTrain.models.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.multipart.MultipartFile;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);

    User findByEmail(String email);

}
