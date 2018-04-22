package org.itstep.controller;

import java.util.List;

import org.itstep.model.Group;
import org.itstep.model.Teacher;
import org.itstep.service.GroupService;
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
@RequestMapping(path = "/group")
public class GroupController {

	@Autowired
	GroupService groupService;

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	ResponseEntity<Group> save(@RequestBody Group group) {
		Group groupInDB = groupService.save(group);
		if (groupInDB != null) {
			return new ResponseEntity<Group>(groupInDB, HttpStatus.OK);
		}
		return new ResponseEntity<Group>(HttpStatus.BAD_REQUEST);
	}

	@PutMapping(consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	ResponseEntity update(@RequestBody Group group) {
		Group groupInDB = groupService.update(group);
		if (groupInDB != null) {
			return new ResponseEntity(groupInDB, HttpStatus.OK);
		}
		return new ResponseEntity(HttpStatus.BAD_REQUEST);
	}

	@GetMapping(path = "/get-one", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	ResponseEntity<Group> getOne(@RequestHeader String name) {
		Group savedGroup = groupService.get(name);
		if (savedGroup != null) {
			return new ResponseEntity<Group>(savedGroup, HttpStatus.OK);
		}
		return new ResponseEntity(HttpStatus.BAD_REQUEST);
	}

	@GetMapping(path = "/get-by-course", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	ResponseEntity<List<Group>> findAllByCourse(@RequestHeader String course) {
		List<Group> groups = groupService.findAllByCourse(course);
		if (groups != null) {
			return new ResponseEntity<List<Group>>(groups, HttpStatus.OK);
		}
		return new ResponseEntity(HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping(consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	ResponseEntity delete(@RequestBody Group group) {

		groupService.delete(group);
		return new ResponseEntity(HttpStatus.OK);

	}
}