package it.egames.dto;

import it.egames.entity.Purchase;
import it.egames.entity.PurchaseVideogame;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseReceiptDTO {
    private Purchase purchase;
    private List<PurchaseVideogame> purchasedVideogameList;
}
