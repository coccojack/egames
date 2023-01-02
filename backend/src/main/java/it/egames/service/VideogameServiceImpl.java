package it.egames.service;

import it.egames.dto.VideogameDTO;
import it.egames.entity.Platform;
import it.egames.entity.Videogame;
import it.egames.repository.PlatformRepository;
import it.egames.repository.VideogameRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class VideogameServiceImpl implements VideogameService {

    @Autowired
    VideogameRepository videogameRepository;

    @Autowired
    PlatformRepository platformRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public Optional<Videogame> insert(VideogameDTO videogameDTO) {
        Videogame videogame = modelMapper.map(videogameDTO, Videogame.class);
        if (Objects.isNull(videogameDTO.getPlatformName())) {
            return Optional.empty();
        }
        Optional<Platform> targetPlatform = platformRepository.findByName(videogameDTO.getPlatformName());
        if (targetPlatform.isEmpty()) {
            return Optional.empty();
        }
        videogame.setPlatform(targetPlatform.get());
        Videogame savedEntity = videogameRepository.save(videogame);
        return Optional.of(savedEntity);
    }

    @Override
    public Optional<Videogame> uploadImage(Long gameId, MultipartFile image) throws IOException {
        Optional<Videogame> gameToUpdate = videogameRepository.findById(gameId);
        if (gameToUpdate.isEmpty()) {
            return Optional.empty();
        }
        Videogame videogame = gameToUpdate.get();
        videogame.setImage(image.getBytes());
        return Optional.of(videogameRepository.save(videogame));
    }

    @Override
    public List<Videogame> getAll() {
//        log.info("{}.getAll - INIT", this.getClass().getSimpleName());
        return videogameRepository.findAll();
    }

    @Override
    public Optional<Videogame> getById(Long gameId) {
        return videogameRepository.findById(gameId);
    }

    @Override
    public List<Videogame> getAllRelated(Long gameId) {
        Optional<Videogame> videogame = getById(gameId);
        if (videogame.isEmpty()) {
            return new ArrayList<>();
        }
        List<Videogame> resultList = videogameRepository.findAllByPlatformAndGenre(videogame.get().getPlatform(), videogame.get().getGenre());
        resultList.remove(videogame.get());
        return resultList;
    }

}
