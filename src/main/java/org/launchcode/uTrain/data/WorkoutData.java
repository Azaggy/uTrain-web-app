package org.launchcode.uTrain.data;

import org.launchcode.uTrain.models.workout.CustomWorkout;
import org.launchcode.uTrain.models.workout.Workout;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class WorkoutData {
    //    need a place to put workout
    private static Map<Integer, CustomWorkout> customworkouts = new HashMap<>();

    //    get all workout
    public static Collection<CustomWorkout> getAll() {
        return customworkouts.values();
    }


    //    get a single workout
    public static CustomWorkout getById(int id) {
        return CustomWorkout.get(id);
    }
    //    add a workout
    public static void add(CustomWorkout customWorkout) {
        customworkouts.put(customWorkout.getId(), customWorkout);
    }

    //    remove an workout
    public static void remove(int id) {
        customworkouts.remove(id);
    }
}
