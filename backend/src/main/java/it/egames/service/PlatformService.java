package it.egames.service;

import it.egames.entity.Platform;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PlatformService {

    List<Platform> getAll();
}
