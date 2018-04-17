package org.itstep.service;

import java.util.List;

import javax.security.auth.Subject;

import org.itstep.model.Group;

public interface SubjectService {

	Subject save(Subject subject);
	 
	Subject update(Subject subject);
	 
	Subject get(String groupName);
	 
	 void delete(Subject subject); 
	 
	 List<Subject> findAllByCourse(String course);
}
