package it.egames.controller;

import it.egames.dto.VideogameDTO;
import it.egames.service.VideogameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping(value = "/videogame")
public class VideogameController {

    @Autowired
    VideogameService videogameService;

    @PostMapping(value = "/add")
    public ResponseEntity<VideogameDTO> addVideogame(@RequestBody VideogameDTO videogameDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(videogameService.insert(videogameDTO));
    }

    @PostMapping(value = "/uploadImage")
    public ResponseEntity<VideogameDTO> uploadImage(@RequestParam Long gameId, @RequestParam MultipartFile image) throws IOException {
        Optional<VideogameDTO> result = videogameService.uploadImage(gameId, image);
        if (result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(result.get());
    }


}
