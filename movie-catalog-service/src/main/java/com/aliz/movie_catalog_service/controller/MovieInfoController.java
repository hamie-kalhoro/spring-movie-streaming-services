package com.aliz.movie_catalog_service.controller;

import com.aliz.movie_catalog_service.model.MovieInfo;
import com.aliz.movie_catalog_service.repository.MovieInfoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie-info")
public class MovieInfoController {

    private final MovieInfoRepository movieInfoRepository;
    public MovieInfoController(MovieInfoRepository movieInfoRepository) {
        this.movieInfoRepository = movieInfoRepository;
    }

    @PostMapping("/save")
    public List<MovieInfo> saveAll(@RequestBody List<MovieInfo> movieInfoList) {
        return movieInfoRepository.saveAll(movieInfoList);
    }

    @GetMapping("/get-all")
    public List<MovieInfo> getAll() {
        return movieInfoRepository.findAll();
    }
}
