package org.launchcode.uTrain.data;

import org.launchcode.uTrain.models.workout.Workout;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public interface WorkoutRepository extends CrudRepository<Workout, Integer> {

    Workout findByTimeStamp(Date timestamp);

}
