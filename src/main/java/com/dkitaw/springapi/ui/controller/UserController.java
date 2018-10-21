package com.dkitaw.springapi.ui.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.dkitaw.springapi.exceptions.UserServiceException;
import com.dkitaw.springapi.service.AddressService;
import com.dkitaw.springapi.service.UserService;
import com.dkitaw.springapi.shared.dto.AddressDto;
import com.dkitaw.springapi.shared.dto.UserDto;
import com.dkitaw.springapi.ui.model.request.UserDetailsRequestModel;
import com.dkitaw.springapi.ui.model.response.AddressesRest;
import com.dkitaw.springapi.ui.model.response.ErrorMessages;
import com.dkitaw.springapi.ui.model.response.OperationStatusModel;
import com.dkitaw.springapi.ui.model.response.RequestOperationName;
import com.dkitaw.springapi.ui.model.response.RequestOperationStatus;
import com.dkitaw.springapi.ui.model.response.UserRest;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
@CrossOrigin(origins = { "http://localhost:3000" })
public class UserController {

  @Autowired
  UserService userService;
  @Autowired
  AddressService addressService;
  @Autowired
  AddressService addressesService;
  
  @GetMapping(path = "/{id}")
  public UserRest getUser(@PathVariable String id) {
    UserRest returnValue = new UserRest();

    UserDto userDto = userService.getUserByUserId(id);
    // BeanUtils.copyProperties(userDto, returnValue);
    ModelMapper modelMapper = new ModelMapper();
    returnValue = modelMapper.map(userDto, UserRest.class);
    return returnValue;
  }

  @GetMapping
  public List<UserRest> getUsers(@RequestParam(value = "page", defaultValue = "0") int page,
      @RequestParam(value = "limit", defaultValue = "25") int limit) {
    List<UserRest> returnValue = new ArrayList<>();
    List<UserDto> users = userService.getUsers(page, limit);
    Type listType = new TypeToken<List<UserRest>>() {
		}.getType();
		returnValue = new ModelMapper().map(users, listType);
    // for (UserDto userDto : users) {
    //   UserRest userModel = new UserRest();
    //   BeanUtils.copyProperties(userDto, userModel);
    //   returnValue.add(userModel);
    // }
    return returnValue;
  }
// http://localhost:8080/users/yXr7jT7UOCSIzpLwJiz3rNmFE2YSbB(userrId)/addresses
  @GetMapping(path="{id}/addresses")
  public List<AddressesRest> getUserAddresses(@PathVariable String id){
    
    List<AddressesRest> returnValue = new ArrayList<>();  
    List<AddressDto> addressesDto = addressService.getAddresses(id);

    if(addressesDto !=null && !addressesDto.isEmpty()){
      java.lang.reflect.Type listType = new TypeToken<List<AddressesRest>>() {}.getType();
      returnValue = new ModelMapper().map(addressesDto, listType);
    }
    return returnValue;
  }

@GetMapping(path = "/{userId}/addresses/{addressId}")
public AddressesRest getUserAddress(@PathVariable String userId, @PathVariable String addressId) {

    AddressDto addressesDto = addressService.getAddress(addressId);

  ModelMapper modelMapper = new ModelMapper();


  return modelMapper.map(addressesDto, AddressesRest.class);
}

  @PostMapping(path="/signup")
  public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception {
    UserRest returnValue = new UserRest();

    if (userDetails.getFirstName().isEmpty())
      throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

    // UserDto userDto = new UserDto();
    // BeanUtils.copyProperties(userDetails, userDto);
    ModelMapper modelMapper = new ModelMapper();
    UserDto userDto = modelMapper.map(userDetails, UserDto.class);

    // UserDto createdUser = userService.createUser(userDto);
    // BeanUtils.copyProperties(createdUser, returnValue);
    UserDto createdUser = userService.createUser(userDto);
    returnValue = modelMapper.map(createdUser, UserRest.class);

    return returnValue;

  }

  @PutMapping(path = "/{id}")
  public UserRest updateUser(@PathVariable String id, @RequestBody UserDetailsRequestModel userDetails) {
    UserRest returnValue = new UserRest();
    UserDto userDto = new UserDto();
    BeanUtils.copyProperties(userDetails, userDto);

    UserDto updatedUser = userService.updateUser(id, userDto);
    BeanUtils.copyProperties(updatedUser, returnValue);
    return returnValue;
  }

  @DeleteMapping(path = "/{id}")
  public OperationStatusModel deleteUser(@PathVariable String id) {

    OperationStatusModel returnValue = new OperationStatusModel();
    returnValue.setOperationName(RequestOperationName.DELETE.name());

    // Delete user
    userService.deleteUser(id);
    returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
    return returnValue;
  }

}
