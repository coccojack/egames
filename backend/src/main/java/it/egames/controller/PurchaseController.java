package it.egames.controller;

import it.egames.dto.PurchaseDTO;
import it.egames.dto.PurchaseReceiptDTO;
import it.egames.dto.VideogameDTO;
import it.egames.entity.Videogame;
import it.egames.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/purchase")
public class PurchaseController {

    @Autowired
    PurchaseService purchaseService;
    @PostMapping(value = "/add")
    public ResponseEntity<PurchaseReceiptDTO> addPurchase(@RequestBody PurchaseDTO purchaseDTO) {
        Optional<PurchaseReceiptDTO> result = purchaseService.purchase(purchaseDTO);
        if (result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(result.get());
    }
}
