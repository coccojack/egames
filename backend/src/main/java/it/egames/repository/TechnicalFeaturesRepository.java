package it.egames.repository;

import it.egames.entity.TechnicalFeatures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnicalFeaturesRepository extends JpaRepository<TechnicalFeatures, Long> {
}
