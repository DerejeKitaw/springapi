package com.dkitaw.springapi.shared.dto;

import java.io.Serializable;

public class ProjectDto implements Serializable {
  private static final long serialVersionUID = 7532240256428563178L;
  private long id;
  private String projectId; 
  private String projectNumber; 
  private String firstName; 
  private String lastName; 
  private String projectName; 
  private String generatedBy; 
  private String salesRepresentativeName;

  public long getId() {
    return this.id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getProjectId() {
    return this.projectId;
  }

  public void setProjectId(String projectId) {
    this.projectId = projectId;
  }

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