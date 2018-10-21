package com.dkitaw.springapi.io.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.dkitaw.springapi.shared.dto.UserDto;

@Entity(name="addresses")
public class AddressEntity implements Serializable{

  private static final long serialVersionUID = 7416688462865725194L;
  @Id
  @GeneratedValue
  private Long id;

  @Column(length=30, nullable=false)
  private String addressId;

  @Column(length=30, nullable=false)
  private String city;

  @Column(length=15, nullable=false)
  private String county;

  @Column(length=100, nullable=false)
  private String streetName;

  @Column(length=7, nullable=false)
  private String postalCode;

  @Column(length=10, nullable=false)
  private String type;

  @ManyToOne
  @JoinColumn(name="users_id")
  private UserEntity userDetails;

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

  public UserEntity getUserDetails() {
    return this.userDetails;
  }

  public void setUserDetails(UserEntity userDetails) {
    this.userDetails = userDetails;
  }
}