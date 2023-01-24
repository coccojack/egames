package it.egames.repository;

import it.egames.entity.Purchase;
import it.egames.entity.PurchaseVideogame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseVideogameRepository extends JpaRepository<PurchaseVideogame, Long> {
    List<PurchaseVideogame> findAllByPurchase(Purchase purchase);
}
