package com.dkitaw.springapi.io.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="projects")
public  class ProjectEntity implements Serializable {
  private static final long serialVersionUID = -7796462016599589809L;
  @Id
  @GeneratedValue
  private long id;
  @Column(nullable=false)
  private String projectId; 

  @Column(nullable=false, length=5)
  private String projectNumber; 

  @Column(nullable=false, length=50)
  private String firstName; 

  @Column(nullable=false, length=50)
  private String lastName; 

  @Column(nullable=false, length=50)
  private String projectName; 

  @Column(nullable=false, length=50)
  private String generatedBy; 

  @Column(nullable=false, length=50)
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