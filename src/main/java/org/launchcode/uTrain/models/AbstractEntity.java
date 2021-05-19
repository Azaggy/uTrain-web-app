package org.launchcode.uTrain.models;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@MappedSuperclass
public class AbstractEntity {

    @Id
//    @GenericGenerator(
//            name = "sequenceGenerator",
//            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
//            parameters = {
//                    @Parameter(name = "sequence_name", value = "hibernate_sequence"),
//                    @Parameter(name = "optimizer", value = "pooled"),
//                    @Parameter(name = "initial_value", value = "1000"),
//                    @Parameter(name = "increment_size", value = "1")
//                }
//            )
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @GeneratedValue
    @Column(name = "id")
    private int id;

    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
    }
}
