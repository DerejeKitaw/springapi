package com.dkitaw.springapi.service.impl;

import com.dkitaw.springapi.io.entity.UserEntity;
import com.dkitaw.springapi.io.entity.repository.UserRepository;
import com.dkitaw.springapi.service.UserService;
import com.dkitaw.springapi.shared.Utils;
import com.dkitaw.springapi.shared.dto.UserDto;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  UserRepository userRepository;

  @Autowired
  Utils utils;

  @Autowired
  BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  public UserDto createUser(UserDto user) {

    if (userRepository.findByEmail(user.getEmail()) != null)
      throw new RuntimeException("Record alredy exists");

    UserEntity userEntity = new UserEntity();
    BeanUtils.copyProperties(user, userEntity); // copy userDto to userEntity

    String publicUserId = utils.generateUserId(30);
    userEntity.setUserId(publicUserId);
    userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));

    UserEntity storedUserDetails = userRepository.save(userEntity);

    UserDto returnValue = new UserDto();
    BeanUtils.copyProperties(storedUserDetails, returnValue);// copy storedUserDetails to returnValue

    return returnValue;
  }
}