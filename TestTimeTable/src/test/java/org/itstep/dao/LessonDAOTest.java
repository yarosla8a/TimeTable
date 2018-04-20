package org.itstep.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.itstep.ApplicationRunner;
import org.itstep.model.Group;
import org.itstep.model.Lesson;
import org.itstep.model.Subject;
import org.itstep.model.Teacher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

public class LessonDAOTest {
	Subject subjectInDB;

	Teacher teacherInDB1;
	Teacher teacherInDB2;

	Group groupInDB1;
	Group groupInDB2;

	Lesson lessonInDB;
	Lesson lessonInDB2;

	@Autowired
	LessonDAO lessonDAO;

	@Autowired
	SubjectDAO subjectDAO;

	@Autowired
	TeacherDAO teacherDAO;

	@Autowired
	GroupDAO groupDAO;

	@Before
	public void setToDB() {
		Subject subject = new Subject();
		subject.setName("Java");

		subjectInDB = subjectDAO.save(subject);

		Teacher teacher = new Teacher();
		Teacher teacher2 = new Teacher();
		
		teacher.setFirstName("Alex");
		teacher.setSecondName("Ignatenko");
		teacher.setLogin("Ignatenko2207");
		teacher.setPassword("123456");
		teacher.setSubject(subjectInDB);

		teacher2.setFirstName("Nina");
		teacher2.setSecondName("Petrivna");
		teacher2.setLogin("Petrivna1234");
		teacher2.setPassword("09876");
		teacher2.setSubject(subjectInDB);

		teacherInDB1 = teacherDAO.save(teacher);
		teacherInDB2 = teacherDAO.save(teacher2);

		Group testGroup1 = new Group();
		Group testGroup2 = new Group();

		testGroup1.setName("ST21");
		testGroup1.setCourse("1");
		testGroup1.setSpecialization("JavaQA");

		testGroup2.setName("J3-17");
		testGroup2.setCourse("1");
		testGroup2.setSpecialization("Java Development");

		groupInDB1 = groupDAO.save(testGroup1);
		groupInDB2 = groupDAO.save(testGroup2);

		Lesson less1 = new Lesson();
		Lesson less2 = new Lesson();
		
		less1.setSubject(subjectInDB);
		less1.setTeacher(teacherInDB1);
		less1.setCabinet("111");
		less1.setGroup(groupInDB1);
		less1.setStartTime(12345L);
		less1.setEndTime(87645L);

		less2.setSubject(subjectInDB);
		less2.setTeacher(teacherInDB2);
		less2.setCabinet("222");
		less2.setGroup(groupInDB2);
		less2.setStartTime(12345L);
		less2.setEndTime(87645L);

		lessonInDB = lessonDAO.save(less1);
		lessonInDB2 = lessonDAO.save(less2);

		//  add endTime in LessonDAO

	}

	@Test
	public void testFindAllByStartTime() {
		List<Lesson> lessons = lessonDAO.findAllByStartTime(12345L, 87645L);
		
		assertNotNull(lessons);
		
		assertEquals(2, lessons.size());
		
		
		assertEquals(lessons.get(0).getGroup().getName(), "ST21");
		assertNotEquals(lessons.get(0).getGroup().getName(), lessons.get(1).getGroup().getName());
		
		assertEquals(lessons.get(0).getTeacher().getLogin(), "Ignatenko2207");
		assertNotEquals(lessons.get(0).getTeacher().getLogin(), lessons.get(1).getTeacher().getLogin());
		
		assertEquals(lessons.get(0).getCabinet(), "111");
		assertNotEquals(lessons.get(0).getCabinet(), lessons.get(1).getCabinet());

	}

	@After
	public void cleanDB() {
		lessonDAO.delete(lessonInDB);
		lessonDAO.delete(lessonInDB2);
		groupDAO.delete(groupInDB1);
		groupDAO.delete(groupInDB2);
		teacherDAO.delete(teacherInDB1);
		teacherDAO.delete(teacherInDB2);
		subjectDAO.delete(subjectInDB);
		

	}

}
