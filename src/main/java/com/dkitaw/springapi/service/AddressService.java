package com.dkitaw.springapi.service;

import java.util.List;

import com.dkitaw.springapi.shared.dto.AddressDto;

public interface AddressService {
List<AddressDto> getAddresses(String userId);
AddressDto getAddress(String addressId);
}