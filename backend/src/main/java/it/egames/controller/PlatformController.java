package it.egames.controller;

import it.egames.entity.Platform;
import it.egames.service.PlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/platform")
public class PlatformController {

    @Autowired
    PlatformService platformService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<Platform>> getAll() {
        List<Platform> result = platformService.getAll();
        if (result.isEmpty()) {
            return ResponseEntity.status((HttpStatus.NO_CONTENT)).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
