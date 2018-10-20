package com.dkitaw.springapi.exceptions;

public class UserServiceException extends RuntimeException{


  private static final long serialVersionUID = 7108490136764469282L;

  public UserServiceException(String message) {
    super(message);
  }

}