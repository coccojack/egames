package it.egames.service;

import it.egames.dto.VideogameDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public interface VideogameService {
    VideogameDTO insert(VideogameDTO videogameDTO);

    Optional<VideogameDTO> uploadImage(Long gameId, MultipartFile image) throws IOException;
}
