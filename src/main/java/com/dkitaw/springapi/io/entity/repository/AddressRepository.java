package com.dkitaw.springapi.io.entity.repository;

import java.util.List;

import com.dkitaw.springapi.io.entity.AddressEntity;
import com.dkitaw.springapi.io.entity.UserEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<AddressEntity, Long> {
	List<AddressEntity> findAllByUserDetails(UserEntity userEntity);
	AddressEntity findByAddressId(String addressId);
}
