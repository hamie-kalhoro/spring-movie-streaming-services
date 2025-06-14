package com.aliz.movie_streaming_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MovieCatalogService {

    public static final String CATALOG_SERVICE = "http://movie-catalog-service";

    private final RestTemplate restTemplate;
    public MovieCatalogService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getMoviePath(Long movieInfoId) {
        ResponseEntity<String> response = restTemplate.getForEntity(
                CATALOG_SERVICE + "/movie-info/find-by-id/{id}",
                String.class,
                movieInfoId
        );
        return response.getBody();
    }
}
