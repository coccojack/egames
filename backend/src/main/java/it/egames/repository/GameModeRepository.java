package it.egames.repository;

import it.egames.entity.GameMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameModeRepository extends JpaRepository<GameMode, Long> {
}
