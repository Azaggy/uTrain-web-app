package org.launchcode.uTrain.data;

import org.launchcode.uTrain.models.user.UserPhoto;
import org.springframework.data.repository.CrudRepository;

public interface PhotoRepository extends CrudRepository<UserPhoto, Integer> {

    UserPhoto findById(int userId);
}
