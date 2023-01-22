package it.egames.controller;

import it.egames.dto.entity.Genre;
import it.egames.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/genre")
public class GenreController {

    @Autowired
    GenreService genreService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<Genre>> getAll() {
        List<Genre> result = genreService.getAll();
        if (result.isEmpty()) {
            return ResponseEntity.status((HttpStatus.NO_CONTENT)).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
