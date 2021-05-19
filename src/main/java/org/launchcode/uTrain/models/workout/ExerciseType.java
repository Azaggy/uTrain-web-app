package org.launchcode.uTrain.models.workout;

public enum ExerciseType {

    ACTIVERECOVERY("Active Recovery"),
    AEROBICEXERCISE("Aerobic Exercise"),
    ANAEROBICEXERCISE("Anaerobic Exercise"),
    BOOTCAMP("Boot Camp"),
    CROSSTRAINING("Cross Training"),
    EVERYDAYMOVES("Everyday Moves"),
    INTERVALS("Intervals"),
    ISOMETRICS("Isometrics"),
    MUSCLETRAINING("Muscle Training"),
    REPETITIONS("Repetitions"),
    STEADYCARDIO("Steady-Cardio");



    private final String exerciseType;

    ExerciseType(String exerciseType) { this.exerciseType = exerciseType; }

    public String getExerciseType() {
        return exerciseType;
    }
}

