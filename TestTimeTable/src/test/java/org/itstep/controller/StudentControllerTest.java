package org.itstep.controller;

import static org.junit.Assert.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.itstep.ApplicationRunner;
import org.itstep.model.Group;
import org.itstep.model.Student;
import org.itstep.service.StudentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {

	@MockBean
	StudentService studentService;

	@Autowired
	TestRestTemplate restTemplate;

	List<Student> students = new ArrayList<Student>();

	@Before
	public void setUp() throws Exception {
		for (int i = 0; i <= 3; i++) {
			Group group = new Group();
			group.setName("ST21");

			Student student = new Student();
			student.setLogin("Repa234.com" + i);
			student.setPassword("0987");
			student.setGroup(group);

			students.add(student);

		}
	}

	@Test
	public void testSave() throws URISyntaxException {
		Mockito.when(studentService.save(Mockito.any(Student.class))).thenReturn(students.get(0));

		RequestEntity<Student> request = new RequestEntity<Student>(students.get(0), HttpMethod.POST,
				new URI("/student"));

		ResponseEntity<Student> response = restTemplate.exchange(request, Student.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());

		Mockito.verify(studentService, Mockito.times(1)).save(Mockito.any(Student.class));

	}

	@Test
	public void testUpdate() throws URISyntaxException {
		Mockito.when(studentService.update(Mockito.any(Student.class))).thenReturn(students.get(0));

		RequestEntity<Student> request = new RequestEntity<Student>(students.get(0), HttpMethod.PUT,
				new URI("/student"));

		ResponseEntity<Student> response = restTemplate.exchange(request, Student.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());

		Mockito.verify(studentService, Mockito.times(1)).update(Mockito.any(Student.class));

	}

	@Test
	public void testGetOne() throws URISyntaxException {
		Mockito.when(studentService.get(Mockito.anyString())).thenReturn(students.get(0));

		HttpHeaders headers = new HttpHeaders();
		headers.add("login", "Repa234.com");

		RequestEntity<Student> request = new RequestEntity<Student>(headers, HttpMethod.GET,
				new URI("/student/get-one"));
		ResponseEntity<Student> response = restTemplate.exchange(request, Student.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		Mockito.verify(studentService, Mockito.times(1)).get(Mockito.anyString());
	}

	@Test
	public void testFindAllByGroup() throws URISyntaxException {
		Mockito.when(studentService.findAllByGroup(Mockito.anyString())).thenReturn(students);

		HttpHeaders headers = new HttpHeaders();
		headers.add("name", "ST21");

		RequestEntity request = new RequestEntity(headers, HttpMethod.GET,
				new URI("/student/get-by-group"));
		ResponseEntity<List> response = restTemplate.exchange(request, List.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		Mockito.verify(studentService, Mockito.times(1)).findAllByGroup(Mockito.anyString());

		assertEquals(4, response.getBody().size());

	}

	@Test
	public void testDelete() throws URISyntaxException {
		Mockito.doNothing().when(studentService).delete(Mockito.any(Student.class));

		RequestEntity<Student> request = new RequestEntity<Student>(students.get(0), HttpMethod.DELETE,
				new URI("/student"));

		ResponseEntity<HttpStatus> response = restTemplate.exchange(request, HttpStatus.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());

		Mockito.verify(studentService, Mockito.times(1)).delete(Mockito.any(Student.class));
	}

}
