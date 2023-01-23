package it.egames.service;

import it.egames.entity.Address;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface AddressService {
    Set<Address> getAddressSetFromCustomerId(Long customerId);
}
