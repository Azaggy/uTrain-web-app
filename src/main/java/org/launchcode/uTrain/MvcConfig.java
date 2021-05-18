package org.launchcode.uTrain;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers (ResourceHandlerRegistry registry) {
        Path imageUploadDir = Paths.get("./src/main/resources/templates/user/profilePic");
        String uploadPath = imageUploadDir.toFile().getAbsolutePath();

        registry.addResourceHandler("/profilePic/**").addResourceLocations("file:/" + uploadPath + "/");

//        exposeDirectory("profilePic", registry);
    }
//
//    private void exposeDirectory(String dirName, ResourceHandlerRegistry registry) {
//        Path uploadDir = Paths.get(dirName);
//        String uploadPath = uploadDir.toFile().getAbsolutePath();
//
//        if (dirName.startsWith("../")) dirName = dirName.replace("../", " ");
//
//        registry.addResourceHandler("/" + dirName + "/**").addResourceLocations("file:/" + uploadPath + "/");
//    }
}
