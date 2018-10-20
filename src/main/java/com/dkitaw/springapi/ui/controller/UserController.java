package com.dkitaw.springapi.ui.controller;

import com.dkitaw.springapi.exceptions.UserServiceException;
import com.dkitaw.springapi.service.UserService;
import com.dkitaw.springapi.shared.dto.UserDto;
import com.dkitaw.springapi.ui.model.request.UserDetailsRequestModel;
import com.dkitaw.springapi.ui.model.response.ErrorMessages;
import com.dkitaw.springapi.ui.model.response.OperationStatusModel;
import com.dkitaw.springapi.ui.model.response.RequestOperationName;
import com.dkitaw.springapi.ui.model.response.RequestOperationStatus;
import com.dkitaw.springapi.ui.model.response.UserRest;

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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
@CrossOrigin(origins = { "http://localhost:3000" })
public class UserController {

  @Autowired
  UserService userService;

  @GetMapping(path = "/{id}")
  public UserRest getUser(@PathVariable String id) {
    UserRest returnValue = new UserRest();
    UserDto userDto = userService.getUserByUserId(id);
    BeanUtils.copyProperties(userDto, returnValue);

    return returnValue;
  }

  @PostMapping
  public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception {
    UserRest returnValue = new UserRest();
    if (userDetails.getFirstName().isEmpty())
      throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
    UserDto userDto = new UserDto();
    BeanUtils.copyProperties(userDetails, userDto);

    UserDto createdUser = userService.createUser(userDto);
    BeanUtils.copyProperties(createdUser, returnValue);

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
