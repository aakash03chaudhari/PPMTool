package com.projects.ppmtool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projects.ppmtool.entity.Project;
import com.projects.ppmtool.exceptions.ProjectIdException;
import com.projects.ppmtool.repository.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	public Project saveOrUpdateProject(Project project) {
		try {
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			return projectRepository.save(project);
		} catch (Exception e) {
			throw new ProjectIdException(
					"Project ID '" + project.getProjectIdentifier().toUpperCase() + "' already exists");
		}
	}

	public Project findProjectByIdentifier(String projectIdentifier) {
		Project project = projectRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());
		if (project == null) {
			throw new ProjectIdException("Project ID '" + projectIdentifier + "' does not exist");
		}
		return project;
	}

	public Iterable<Project> findAllProjects() {
		return projectRepository.findAll();
	}

	public void deleteProjectByIdentifier(String projectIdentifier) {
		Project project = projectRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());
		if (project == null) {
			throw new ProjectIdException(
					"Cannot Delete with Project ID '" + projectIdentifier + "'. This project does not exist");
		}
		projectRepository.delete(project);
	}
}
