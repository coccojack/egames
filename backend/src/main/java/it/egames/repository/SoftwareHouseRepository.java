package it.egames.repository;

import it.egames.dto.entity.SoftwareHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoftwareHouseRepository extends JpaRepository<SoftwareHouse, Long> {
}
