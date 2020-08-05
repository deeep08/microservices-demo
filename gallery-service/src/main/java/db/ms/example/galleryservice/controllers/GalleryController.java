package db.ms.example.galleryservice.controllers;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import db.ms.example.galleryservice.model.Gallery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class GalleryController {

    private static final Logger logger = LoggerFactory.getLogger(GalleryController.class);

    @Autowired
    private Environment env;

    @Autowired
    @LoadBalanced
    private RestTemplate restTemplate;

    @GetMapping("/")
    public String home() {
        return "Hello from Gallery Service running at port: " + env.getProperty("server.port");
    }

    @HystrixCommand(fallbackMethod = "fallback")
    @GetMapping("/{id}")
    public Gallery getGallery(@PathVariable int id) {
        Gallery gallery = new Gallery(id);

        List<Object> images = restTemplate.getForObject(env.getProperty("image.service.url"), List.class);
        gallery.setImages(images);

        return gallery;
    }

    @GetMapping("/admin")
    public String adminHome() {
        return "Hello Admin from Gallery Service running at port: " + env.getProperty("server.port");
    }

    public Gallery fallback(int galleryId, Throwable error) {
        logger.warn("Fallback method called by Circuit breaker: " + error.getMessage());
        return new Gallery(galleryId);
    }
}
