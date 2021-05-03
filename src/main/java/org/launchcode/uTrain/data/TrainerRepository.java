package org.launchcode.uTrain.data;

import org.launchcode.uTrain.models.Trainer;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainerRepository extends CrudRepository<Trainer, Integer> {
}
