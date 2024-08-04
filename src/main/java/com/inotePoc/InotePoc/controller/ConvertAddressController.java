package com.inotePoc.InotePoc.controller;


import com.inotePoc.InotePoc.model.AddressEntity;
import com.inotePoc.InotePoc.model.AddressUpdateRequest;
import com.inotePoc.InotePoc.model.StructuredAddress;
import com.inotePoc.InotePoc.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
public class ConvertAddressController {

    @Autowired
    private AddressService addressService;



    @GetMapping("checkSystem")
    private String checkSystem(){

        return "System Up and Running";
    }


    @GetMapping("/getAllAddress")
    public ResponseEntity<List<AddressEntity>>getAddress(){

        log.info("get ALL Address inside Controller");

        List<AddressEntity> addressList = new ArrayList<>();

        try {
            addressList = addressService.getAlladdress();
            return ResponseEntity.ok().body(addressList);
        }catch (Exception e){

            log.error("can not get Address");
            return ResponseEntity.internalServerError().body(addressList);
        }
    }

    @PostMapping("/saveAddress")
    public ResponseEntity<String>saveAddressToDB(@RequestBody AddressEntity addressEntity){

        log.info("Sacving Address to DB inside Conteoller");

        String value = addressService.saveAddress(addressEntity);

            if (!value.isBlank())
                    return ResponseEntity.ok(value);
            else
                return ResponseEntity.internalServerError().body("failed");


    }

   @PutMapping("/updateAddress")
    public ResponseEntity<String> updateStructuredAddress(@RequestBody AddressUpdateRequest request) {
        String addressId = request.getAddressId();
        StructuredAddress structuredAddress = request.getStructuredAddress();

        log.info("Updating address for the Id " + addressId);

        try {
            boolean isUpdated = addressService.updateAddress(structuredAddress, addressId);

            if (isUpdated) {
                return ResponseEntity.ok().body("Updated address for Id: " + addressId);
            } else {
                return ResponseEntity.internalServerError().body("Failed");
            }
        } catch (Exception e) {
            log.error("Error updating address", e);
            return ResponseEntity.internalServerError().body("Failed");
        }
    }

}
