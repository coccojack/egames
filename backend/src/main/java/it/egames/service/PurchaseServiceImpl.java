package it.egames.service;

import it.egames.dto.PurchaseDTO;
import it.egames.dto.PurchaseReceiptDTO;
import it.egames.entity.*;
import it.egames.repository.*;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.Date;
import java.util.*;

@Service
public class PurchaseServiceImpl implements PurchaseService{
    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    VideogameRepository videogameRepository;

    @Autowired
    PurchaseVideogameRepository purchaseVideogameRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    StatusRepository statusRepository;

    @Override
    public Optional<PurchaseReceiptDTO> purchase(PurchaseDTO purchaseDTO) {

        Optional<Status> workingStatus=statusRepository.findByName("IN LAVORAZIONE");
        if(workingStatus.isEmpty()){
            return Optional.empty();
        }

        //Map<String,Integer> purchaseList=purchaseDTO.getPurchaseList();
        Map<String, Integer> purchaseList = new HashMap<String, Integer>();
        String s = purchaseDTO.getPurchaseList();
        String[] pairs = s.split(",");
        for (int i=0;i<pairs.length;i++) {
            String pair = pairs[i];
            String[] keyValue = pair.split(":");
            String tmp=keyValue[0].replace("\"","");
            String tmp2=tmp.replace("{","");
            purchaseList.put(tmp2, Integer.valueOf(keyValue[1].replace("}","")));
        }

        Optional<Address> targetAddress=addressRepository.findById(purchaseDTO.getAddressId());
        if(targetAddress.isEmpty()){
            return Optional.empty();
        }

        Optional<Customer> targetCustomer=customerRepository.findById(purchaseDTO.getCustomerId());
        if(targetCustomer.isEmpty()){
            return Optional.empty();
        }

        Purchase purchase=new Purchase();
        purchase.setStatus(workingStatus.get());
        purchase.setAddress(targetAddress.get());
        purchase.setCustomer(targetCustomer.get());
        purchase.setTotal((purchaseDTO.getTotal()));

        Purchase savedPurchase=purchaseRepository.save(purchase);

        //TODO: setdate
        purchase.setPurchaseDate(new Date(DateTime.now().getMillis()));
        List<PurchaseVideogame> purchaseVideogameList=new ArrayList<>();
        for(String gameId : purchaseList.keySet()){
            Optional<Videogame> targetGame=videogameRepository.findById(Long.parseLong(gameId));
            PurchaseVideogame purchaseVideogame=new PurchaseVideogame();
            if(targetGame.isEmpty()){
                return Optional.empty();
            }
            purchaseVideogame.setVideogame(targetGame.get());
            purchaseVideogame.setQuantity(purchaseList.get(gameId));
            purchaseVideogame.setPurchase(savedPurchase);
            purchaseVideogameList.add (purchaseVideogameRepository.save(purchaseVideogame));
        }

        PurchaseReceiptDTO purchaseReceiptDTO=new PurchaseReceiptDTO();
        purchaseReceiptDTO.setPurchase(savedPurchase);
        purchaseReceiptDTO.setPurchasedVideogameList(purchaseVideogameList);
        return Optional.of(purchaseReceiptDTO);
    }

    @Override
    public List<PurchaseReceiptDTO> getAllPurchaseReceipt() {
        List<PurchaseReceiptDTO> results = new ArrayList<>();
        List<Purchase> purchases=purchaseRepository.findAll();
        for(Purchase purchase : purchases){
            PurchaseReceiptDTO purchaseReceiptDTO=new PurchaseReceiptDTO();
            purchaseReceiptDTO.setPurchase(purchase);
            purchaseReceiptDTO.setPurchasedVideogameList(purchaseVideogameRepository.findAllByPurchase(purchase));
            results.add(purchaseReceiptDTO);
        }
        return results;
    }
}
