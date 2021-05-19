package org.launchcode.uTrain.data;

import org.launchcode.uTrain.models.Gym;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GymRepository extends CrudRepository<Gym, Integer> {
}
