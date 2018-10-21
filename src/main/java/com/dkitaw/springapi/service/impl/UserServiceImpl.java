package com.dkitaw.springapi.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.dkitaw.springapi.exceptions.UserServiceException;
import com.dkitaw.springapi.io.entity.UserEntity;
import com.dkitaw.springapi.io.entity.repository.UserRepository;
import com.dkitaw.springapi.service.UserService;
import com.dkitaw.springapi.shared.Utils;
import com.dkitaw.springapi.shared.dto.AddressDto;
import com.dkitaw.springapi.shared.dto.UserDto;
import com.dkitaw.springapi.ui.model.response.ErrorMessages;

import org.springframework.security.core.userdetails.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    for(int i=0; i<user.getAddresses().size(); i++){
      AddressDto address = user.getAddresses().get(i);
      address.setUserDetails(user);
      address.setAddressId(utils.generateAddressId(30));
      user.getAddresses().set(i, address);
    }

    // BeanUtils.copyProperties(user, userEntity); // copy userDto to userEntity
    ModelMapper modelMapper = new ModelMapper();
    UserEntity userEntity = modelMapper.map(user, UserEntity.class);

    String publicUserId = utils.generateUserId(30);
    userEntity.setUserId(publicUserId);
    userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));

    UserEntity storedUserDetails = userRepository.save(userEntity);

    // BeanUtils.copyProperties(storedUserDetails, returnValue);// copy storedUserDetails to returnValue
    UserDto returnValue = modelMapper.map(storedUserDetails, UserDto.class);
    return returnValue;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    UserEntity userEntity = userRepository.findByEmail(email);
    if (userEntity == null)
      throw new UsernameNotFoundException(email);
    return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
  }

  @Override
  public UserDto getUser(String email) {
    UserEntity userEntity = userRepository.findByEmail(email);
    if (userEntity == null)
      throw new UsernameNotFoundException(email);
    UserDto returnValue = new UserDto();
    BeanUtils.copyProperties(userEntity, returnValue);
    return returnValue;
  }

  @Override
  public UserDto getUserByUserId(String userId) {
    UserDto returnValue = new UserDto();
    UserEntity userEntity = userRepository.findByUserId(userId);

    if (userEntity == null)
      throw new UsernameNotFoundException("User with ID:  " + userId + " not found");

    BeanUtils.copyProperties(userEntity, returnValue);

    return returnValue;
  }

  @Override
  public UserDto updateUser(String userId, UserDto user) {
    UserDto returnValue = new UserDto();
    UserEntity userEntity = userRepository.findByUserId(userId);

    if (userEntity == null) {
      throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
    }
    userEntity.setFirstName(user.getFirstName());
    userEntity.setLastName(user.getLastName());

    UserEntity updatedUserDetails = userRepository.save(userEntity);
    BeanUtils.copyProperties(updatedUserDetails, returnValue);
    return returnValue;
  }

  @Override
  public void deleteUser(String userId) {
    // Get user to delete
    UserEntity userEntity = userRepository.findByUserId(userId);
    // Check if userId is not null
    if (userEntity == null)
      throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
    // If user exist delete user
    userRepository.delete(userEntity);
  }

  @Override
  public List<UserDto> getUsers(int page, int limit) {
    List<UserDto> returnValue = new ArrayList<>();

    if (page > 0)
      page = page - 1; // avoid page=0

    Pageable pageableRequest = PageRequest.of(page, limit);

    Page<UserEntity> userPage = userRepository.findAll(pageableRequest);
    List<UserEntity> users = userPage.getContent();

    for (UserEntity userEntity : users) {
      UserDto userDto = new UserDto();
      BeanUtils.copyProperties(userEntity, userDto);
      returnValue.add(userDto);
    }
    return returnValue;
  }
}