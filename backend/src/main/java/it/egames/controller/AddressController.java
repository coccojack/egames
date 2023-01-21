package it.egames.controller;

import it.egames.entity.Address;
import it.egames.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping(value = "/address")
public class AddressController {
    @Autowired
    AddressService addressService;

    @GetMapping(value = "/getByCustomerId")
    public ResponseEntity<Set<Address>> getByCustomerId(@RequestParam Long customerId) {
        Set<Address> result = addressService.getAddressSetFromCustomerId(customerId);
        if (result.isEmpty()) {
            return ResponseEntity.status((HttpStatus.NO_CONTENT)).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
