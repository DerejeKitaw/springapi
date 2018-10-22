package com.dkitaw.springapi.ui.model.request;

public class ProjectDetailRequestModel {
  private String projectNumber; 
  private String firstName; 
  private String lastName; 
  private String projectName; 
  private String generatedBy; 
  private String salesRepresentativeName;

  public String getProjectNumber() {
    return this.projectNumber;
  }

  public void setProjectNumber(String projectNumber) {
    this.projectNumber = projectNumber;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getProjectName() {
    return this.projectName;
  }

  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }

  public String getGeneratedBy() {
    return this.generatedBy;
  }

  public void setGeneratedBy(String generatedBy) {
    this.generatedBy = generatedBy;
  }

  public String getSalesRepresentativeName() {
    return this.salesRepresentativeName;
  }

  public void setSalesRepresentativeName(String salesRepresentativeName) {
    this.salesRepresentativeName = salesRepresentativeName;
  }
}