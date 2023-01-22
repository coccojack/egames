package it.egames.service;

import it.egames.dto.entity.Genre;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GenreService {

    List<Genre> getAll();
}
