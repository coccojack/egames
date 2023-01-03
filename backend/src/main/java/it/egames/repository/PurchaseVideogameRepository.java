package it.egames.repository;

import it.egames.entity.PurchaseVideogame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseVideogameRepository extends JpaRepository<PurchaseVideogame, Long> {
}
