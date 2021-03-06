package com.projects.ppmtool.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.projects.ppmtool.entity.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

	Project findByProjectIdentifier(String projectIdentifier);

	@Override
	Iterable<Project> findAll();
}
