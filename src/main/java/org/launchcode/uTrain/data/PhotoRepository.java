package org.launchcode.uTrain.data;

import org.launchcode.uTrain.models.UserPhoto;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PhotoRepository extends CrudRepository<UserPhoto, Integer> {

    UserPhoto findById(int userId);
}
