package org.itstep.controller;

import java.util.List;

import org.itstep.model.Teacher;
import org.itstep.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/teacher")
public class TeacherController {

	@Autowired
	TeacherService teacherService;

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	ResponseEntity<Teacher> save(@RequestBody Teacher teacher) {
		Teacher teacherInDB = teacherService.save(teacher);
		if (teacherInDB != null) {
			return new ResponseEntity<Teacher>(teacherInDB, HttpStatus.OK);
		}
		return new ResponseEntity<Teacher>(HttpStatus.BAD_REQUEST);
	}

	@PutMapping(consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	ResponseEntity update(@RequestBody Teacher teacher) {
		Teacher teacherInDB = teacherService.update(teacher);
		if (teacherInDB != null) {
			return new ResponseEntity(teacherInDB, HttpStatus.OK);
		}
		return new ResponseEntity(HttpStatus.BAD_REQUEST);
	}

	@GetMapping(path = "/get-one",  produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	ResponseEntity<Teacher> getOne(@RequestHeader String login) {
		Teacher teacher = teacherService.get(login);
		if (teacher != null) {
			return new ResponseEntity<Teacher>(teacher, HttpStatus.OK);
		}
		return new ResponseEntity<Teacher>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping(path = "/get-by-subject", consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	ResponseEntity<List<Teacher>> findAllBySubject(@RequestHeader String name) {
		List<Teacher> teachers = teacherService.findAllBySubject(name);
		if (teachers != null) {
			return new ResponseEntity<List<Teacher>>(teachers, HttpStatus.OK);
		}
		return new ResponseEntity<List<Teacher>>(HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping(consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	ResponseEntity delete(@RequestBody Teacher teacher) {

		teacherService.delete(teacher);
		return new ResponseEntity(HttpStatus.OK);

	}
}