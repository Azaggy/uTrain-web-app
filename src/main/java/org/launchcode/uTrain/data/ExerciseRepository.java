package org.launchcode.uTrain.data;

import org.launchcode.uTrain.models.workout.Exercise;
import org.launchcode.uTrain.models.workout.ExerciseType;
import org.springframework.data.repository.CrudRepository;

public interface ExerciseRepository extends CrudRepository<Exercise, Integer> {

    Exercise findByName (String name);
    Exercise findByExerciseType (ExerciseType exerciseType);
    Exercise findByUserId (int userId);

}
