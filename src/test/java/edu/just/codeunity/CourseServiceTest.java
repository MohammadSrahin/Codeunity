package edu.just.codeunity;

import edu.just.codeunity.entities.Course;
import edu.just.codeunity.services.CourseService;
import java.util.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.*;

@SpringBootTest
class CourseServiceTest {

  @Autowired
  private CourseService courseService;

  private Course course;

  @BeforeEach
  public void setup() {
    course = new Course();
    course.setName("Test Course");
  }

  @Test
  void testSaveCourse() {
    Course savedCourse = courseService.saveCourse(course);
    Assertions.assertNotNull(savedCourse.getId());
  }

  @Test
  void testGetCourseById() {
    Course savedCourse = courseService.saveCourse(course);
    Long courseId = savedCourse.getId();

    Course retrievedCourse = courseService.getCourseById(courseId);
    Assertions.assertEquals(course.getName(), retrievedCourse.getName());
  }

  @Test
  void testDeleteCourse() {
    Course savedCourse = courseService.saveCourse(course);
    Long courseId = savedCourse.getId();

    courseService.deleteCourse(savedCourse);

    Assertions.assertThrows(NoSuchElementException.class, () -> {
      courseService.getCourseById(courseId);
    });
  }
}
