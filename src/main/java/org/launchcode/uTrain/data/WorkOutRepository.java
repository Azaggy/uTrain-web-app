package org.launchcode.uTrain.data;

import org.launchcode.uTrain.models.workout.Workout;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public interface WorkOutRepository extends CrudRepository<Workout, Integer> {

    Workout findByDescription(String description);

    Workout findByTimeStamp(Date timeStamp);

}

