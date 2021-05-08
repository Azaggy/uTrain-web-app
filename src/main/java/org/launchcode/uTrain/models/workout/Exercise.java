package org.launchcode.uTrain.models.workout;

import org.launchcode.uTrain.models.AbstractEntity;

import javax.persistence.Entity;

@Entity
public class Exercise extends AbstractEntity {

    private String name;

    private ExerciseType exerciseType;

    public Exercise () {}

    public Exercise (String name, ExerciseType exerciseType) {
        this.name = name;
        this.exerciseType = exerciseType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ExerciseType getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(ExerciseType exerciseType) {
        this.exerciseType = exerciseType;
    }
}
