package com.aliz.movie_catalog_service.controller;

import com.aliz.movie_catalog_service.model.MovieInfo;
import com.aliz.movie_catalog_service.repository.MovieInfoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/find-by-id/{id}")
    public String findPathById(@PathVariable Long id) {
        Optional<MovieInfo> byId = movieInfoRepository.findById(id);
        return byId.map(MovieInfo::getPath).orElseThrow(
                () -> new RuntimeException("Movie not found with id: " + id));
    }
}
