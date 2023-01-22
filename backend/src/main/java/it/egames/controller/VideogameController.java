package it.egames.controller;

import it.egames.dto.VideogameDTO;
import it.egames.dto.entity.Videogame;
import it.egames.service.VideogameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/videogame")
public class VideogameController {

    @Autowired
    VideogameService videogameService;

    @PostMapping(value = "/add")
    public ResponseEntity<Videogame> addVideogame(@RequestBody VideogameDTO videogameDTO) {
        Optional<Videogame> result = videogameService.insert(videogameDTO);
        if (result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(result.get());
    }

    @PostMapping(value = "/uploadImage")
    public ResponseEntity<Videogame> uploadImage(@RequestParam Long gameId, @RequestParam MultipartFile image) throws IOException {
        Optional<Videogame> result = videogameService.uploadImage(gameId, image);
        if (result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(result.get());
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Videogame>> getAll() {
        List<Videogame> result = videogameService.getAll();
        if (result.isEmpty()) {
            return ResponseEntity.status((HttpStatus.NO_CONTENT)).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/id/{gameId}")
    public ResponseEntity<Videogame> getVideoGameById(@PathVariable String gameId) throws NumberFormatException {
        Long id = Long.parseLong(gameId);
        Optional<Videogame> response = videogameService.getById(id);
        if (response.isEmpty()) {
            return ResponseEntity.status((HttpStatus.NO_CONTENT)).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(response.get());
    }

    @GetMapping(value = "/related/{gameId}")
    public ResponseEntity<List<Videogame>> getAllRelated(@PathVariable String gameId) throws NumberFormatException {
        Long id = Long.parseLong(gameId);
        List<Videogame> result = videogameService.getAllRelated(id);
        if (result.isEmpty()) {
            return ResponseEntity.status((HttpStatus.NO_CONTENT)).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


}
