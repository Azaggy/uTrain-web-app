package org.launchcode.uTrain.data;

import org.launchcode.uTrain.models.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Integer> {}

