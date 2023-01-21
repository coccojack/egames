package it.egames.service;

import it.egames.entity.Address;
import it.egames.repository.AddressRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Slf4j
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressRepository addressRepository;

    @Override
    public Set<Address> getAddressSetFromCustomerId(Long customerId) {
        return addressRepository.findAllByCustomerId(customerId);
    }
}
