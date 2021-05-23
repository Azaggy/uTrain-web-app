package org.launchcode.uTrain.service;

import org.launchcode.uTrain.data.VideoRepository;
import org.launchcode.uTrain.models.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.print.Doc;
import java.util.List;
import java.util.Optional;

@Service
public class VideoStorageService {

    @Autowired
    private VideoRepository videoRepository;

    public Video saveFile(MultipartFile file) {
        String vidName = file.getOriginalFilename();
        try {
            Video video = new Video(vidName, file.getContentType(), file.getBytes());
            return videoRepository.save(video);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public Optional<Video> getFile(Integer fileId) {
        return videoRepository.findById(fileId);
    }
    public List<Video> getFiles() {
        return videoRepository.findAll();
    }
}
