package org.launchcode.uTrain.data;

import org.launchcode.uTrain.models.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MessageRepository  extends CrudRepository <Message, Integer> {
}

