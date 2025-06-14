package com.aliz.movie_streaming_service.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Slf4j
@RestController
@RequestMapping("/stream")
public class MovieStreamController {

    public static final String VIDEO_DIRECTORY = "D:\\Stream\\";

    private final MovieCatalogService movieCatalogService;
    public MovieStreamController(MovieCatalogService movieCatalogService) {
        this.movieCatalogService = movieCatalogService;
    }

    @GetMapping("/{videoPath}")
    public ResponseEntity<InputStreamResource> streamVideo(
                @PathVariable("videoPath") String videoPath) throws FileNotFoundException {
        File file = new File(VIDEO_DIRECTORY + videoPath);
        if (file.exists()) {
            InputStreamResource input = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType("video/mp4"))
                    .body(input);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("with-id/{videoInfoId}")
    public ResponseEntity<InputStreamResource> streamVideoById(
                @PathVariable Long videoInfoId) throws FileNotFoundException {
        String moviePath = movieCatalogService.getMoviePath(videoInfoId);
        log.info("Resolved movie path: {}", moviePath);
        return streamVideo(moviePath);
    }
}
