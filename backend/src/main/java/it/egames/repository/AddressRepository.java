package it.egames.repository;

import it.egames.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    Set<Address> findAllByCustomerId(Long customerId);
}
