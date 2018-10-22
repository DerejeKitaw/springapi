package com.dkitaw.springapi.ui.controller;

import com.dkitaw.springapi.service.ProjectService;
import com.dkitaw.springapi.shared.dto.ProjectDto;
import com.dkitaw.springapi.ui.model.request.ProjectDetailRequestModel;
import com.dkitaw.springapi.ui.model.response.ProjectRest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping("projects")
public class ProjectController {
  @Autowired
  ProjectService projectService;

  @PostMapping(path="create")
  public ProjectRest createProject(@RequestBody ProjectDetailRequestModel projectDetails) throws Exception{

    ProjectRest returnValue = new ProjectRest();

    ModelMapper modelMapper = new ModelMapper();
    ProjectDto projectDto = modelMapper.map(projectDetails, ProjectDto.class);

    ProjectDto createProject = projectService.createProject(projectDto);
    returnValue = modelMapper.map(createProject, ProjectRest.class);
    return returnValue;
  }
}