package it.egames.service;

import it.egames.entity.Platform;
import it.egames.repository.PlatformRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlatformServiceImpl implements PlatformService {

    @Autowired
    PlatformRepository platformRepository;

    @Override
    public List<Platform> getAll() {
        return platformRepository.findAll();
    }
}
