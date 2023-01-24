package it.egames.dto;

import it.egames.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDTO {
    Long customerId;
    Long addressId;
    Float total;
    //itemId,quantity
    String purchaseList;
}
