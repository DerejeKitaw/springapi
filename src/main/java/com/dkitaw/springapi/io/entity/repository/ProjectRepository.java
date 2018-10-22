package com.dkitaw.springapi.io.entity.repository;

import com.dkitaw.springapi.io.entity.ProjectEntity;

import org.springframework.data.repository.CrudRepository;

public  interface ProjectRepository extends CrudRepository<ProjectEntity, Long>{
ProjectEntity findByProjectNumber(String projectNumber);
}