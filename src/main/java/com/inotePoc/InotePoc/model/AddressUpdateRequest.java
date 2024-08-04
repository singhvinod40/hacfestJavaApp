package com.inotePoc.InotePoc.model;

public class AddressUpdateRequest {
    private StructuredAddress structuredAddress;
    private String addressId;

    // Getters and setters
    public StructuredAddress getStructuredAddress() {
        return structuredAddress;
    }

    public void setStructuredAddress(StructuredAddress structuredAddress) {
        this.structuredAddress = structuredAddress;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }
}
