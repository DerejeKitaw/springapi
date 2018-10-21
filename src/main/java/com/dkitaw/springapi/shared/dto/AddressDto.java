package com.dkitaw.springapi.shared.dto;

public class AddressDto {
  private Long id;
  private String addressId;
  private String city;
  private String county;
  private String streetName;
  private String postalCode;
  private String type;
  private UserDto userDetails;

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }
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

  public UserDto getUserDetails() {
    return this.userDetails;
  }

  public void setUserDetails(UserDto userDetails) {
    this.userDetails = userDetails;
  }
}