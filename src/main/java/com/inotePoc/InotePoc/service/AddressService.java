package com.inotePoc.InotePoc.service;

import com.inotePoc.InotePoc.model.AddressEntity;
import com.inotePoc.InotePoc.model.StructuredAddress;
import com.inotePoc.InotePoc.repository.AddressRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;


    public String saveAddress(AddressEntity addressEntity) {

        log.info("Save address " + addressEntity.getStructured() + "\n" + addressEntity.getUnstructured());

        try {
           AddressEntity addressEntity1 = addressRepository.save(addressEntity);
            log.info("Address saved Successfully");
            return addressEntity1.getAddressId();
        } catch (Exception e) {
            log.error("failed to save address");
            e.printStackTrace();
            return "";
        }
    }

    public List<AddressEntity> getAlladdress (){

        List<AddressEntity> listToReturn = new ArrayList<>();

        log.info("getAll Address From DB");

        try {
            listToReturn = addressRepository.findAll();

            // Filter out addresses where all Structured fields are blank or "N/A"
            listToReturn = listToReturn.stream()
                    .map(this::filterStructured)
                    .collect(Collectors.toList());

            return listToReturn;
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }


    private AddressEntity filterStructured(AddressEntity address) {
        if (address.getStructured() != null && areAllValuesBlank(address.getStructured())) {
            // Clear the Structured field if all values are blank or "N/A"
            address.setStructured(new StructuredAddress());
        }
        return address;
    }

    private boolean areAllValuesBlank(StructuredAddress structured) {
        return (isBlank(structured.getAddress()) &&
                isBlank(structured.getBldgNb()) &&
                isBlank(structured.getCtry()) &&
                isBlank(structured.getCtrySubDvsn()) &&
                isBlank(structured.getDept()) &&
                isBlank(structured.getFlr()) &&
                isBlank(structured.getName()) &&
                isBlank(structured.getPstBx()) &&
                isBlank(structured.getPstCd()) &&
                isBlank(structured.getRoom()) &&
                isBlank(structured.getStrtNm()) &&
                isBlank(structured.getTwnNm()));
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    public boolean updateAddress(StructuredAddress structuredAddress, String addressId) {
        log.info("Finding existing value");

        Optional<AddressEntity> addressEntityOptional = addressRepository.findById(addressId);

        if (addressEntityOptional.isPresent()) {
            AddressEntity addressEntity = addressEntityOptional.get();
            addressEntity.setStructured(structuredAddress);
            addressRepository.save(addressEntity);

            log.info("Updated address and new value is: " + addressEntity);
            return true;
        } else {
            log.info("Address is not present in DB");
            return false;
        }
    }

}
