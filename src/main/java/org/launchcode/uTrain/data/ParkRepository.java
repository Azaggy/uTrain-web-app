package org.launchcode.uTrain.data;

import org.launchcode.uTrain.models.Park;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ParkRepository extends CrudRepository<Park, Integer> {


}
