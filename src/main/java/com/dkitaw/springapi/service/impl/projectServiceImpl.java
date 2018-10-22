package com.dkitaw.springapi.service.impl;

import com.dkitaw.springapi.io.entity.ProjectEntity;
import com.dkitaw.springapi.io.entity.repository.ProjectRepository;
import com.dkitaw.springapi.service.ProjectService;
import com.dkitaw.springapi.shared.Utils;
import com.dkitaw.springapi.shared.dto.ProjectDto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class projectServiceImpl implements ProjectService {

  @Autowired
  ProjectRepository projectRepository;

  @Autowired
  Utils utils;
  @Override
  public ProjectDto createProject(ProjectDto project) {
   if(projectRepository.findByProjectNumber(project.getProjectNumber())!=null) {
     throw new RuntimeException("Project Number Exist");
   }
   ModelMapper modelMapper = new ModelMapper();
   ProjectEntity projectEntity = modelMapper.map(project, ProjectEntity.class);

   String publicProjectId = utils.generateProjectId(30);
   projectEntity.setProjectId(publicProjectId);

   ProjectEntity storedProjectDetails = projectRepository.save(projectEntity);
    ProjectDto returnValue = modelMapper.map(storedProjectDetails, ProjectDto.class);
    return returnValue;
	}

}