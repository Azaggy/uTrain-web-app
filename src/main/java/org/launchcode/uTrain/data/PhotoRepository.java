package org.launchcode.uTrain.data;

import org.launchcode.uTrain.models.UserPhoto;
import org.springframework.data.repository.CrudRepository;

public interface PhotoRepository extends CrudRepository<UserPhoto, Integer> {
}
