package it.egames.service;

import it.egames.dto.PurchaseDTO;
import it.egames.dto.PurchaseReceiptDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PurchaseService {
    Optional<PurchaseReceiptDTO> purchase(PurchaseDTO purchaseDTO);
    List<PurchaseReceiptDTO> getAllPurchaseReceipt();
}
