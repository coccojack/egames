package it.egames.service;

import it.egames.dto.VideogameDTO;
import it.egames.dto.entity.Videogame;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public interface VideogameService {
    Optional<Videogame> insert(VideogameDTO videogameDTO);

    Optional<Videogame> uploadImage(Long gameId, MultipartFile image) throws IOException;

    List<Videogame> getAll();

    Optional<Videogame> getById(Long gameId);

    List<Videogame> getAllRelated(Long gameId);
}
