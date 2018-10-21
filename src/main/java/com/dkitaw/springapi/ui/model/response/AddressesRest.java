package com.dkitaw.springapi.ui.model.response;

public  class AddressesRest {
  private String addressId;
  private String city;
  private String county;
  private String streetName;
  private String postalCode;
  private String type;

  public String getAddressId() {
    return this.addressId;
  }

  public void setAddressId(String addressId) {
    this.addressId = addressId;
  }

  public String getCity() {
    return this.city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getCounty() {
    return this.county;
  }

  public void setCounty(String county) {
    this.county = county;
  }

  public String getStreetName() {
    return this.streetName;
  }

  public void setStreetName(String streetName) {
    this.streetName = streetName;
  }

  public String getPostalCode() {
    return this.postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }
}