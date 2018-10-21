package com.dkitaw.springapi.io.entity.repository;

import com.dkitaw.springapi.io.entity.UserEntity;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
// public interface UserRepository extends CrudRepository<UserEntity, Long>{
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long>{

  // If email found do not record
  UserEntity findByEmail(String email);
  UserEntity findByUserId(String userId);

}