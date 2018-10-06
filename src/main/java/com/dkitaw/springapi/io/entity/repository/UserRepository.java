package com.dkitaw.springapi.io.entity.repository;

import com.dkitaw.springapi.io.entity.UserEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long>{

}