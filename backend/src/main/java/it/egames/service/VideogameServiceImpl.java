package it.egames.service;

import it.egames.dto.VideogameDTO;
import it.egames.entity.Videogame;
import it.egames.repository.VideogameRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class VideogameServiceImpl implements VideogameService {

    @Autowired
    VideogameRepository videogameRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public VideogameDTO insert(VideogameDTO videogameDTO) {
        Videogame videogame = modelMapper.map(videogameDTO, Videogame.class);

        Videogame savedEntity = videogameRepository.save(videogame);
        return modelMapper.map(savedEntity, VideogameDTO.class);
    }

    @Override
    public Optional<VideogameDTO> uploadImage(Long gameId, MultipartFile image) throws IOException {
        Optional<Videogame> gameToUpdate = videogameRepository.findById(gameId);
        if (gameToUpdate.isEmpty()) {
            return Optional.empty();
        }
        Videogame videogame = gameToUpdate.get();
        videogame.setImage(image.getBytes());
        return Optional.of(modelMapper.map(videogameRepository.save(videogame), VideogameDTO.class));
    }

}
