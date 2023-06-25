package edu.just.codeunity;

import edu.just.codeunity.entities.Course;
import edu.just.codeunity.entities.Lesson;
import edu.just.codeunity.entities.User;
import edu.just.codeunity.services.CourseService;
import edu.just.codeunity.services.LessonService;
import edu.just.codeunity.services.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SystemTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private UserService userService;

  @Autowired
  private CourseService courseService;

  @Autowired
  private LessonService lessonService;

  private User testUser;
  private Course testCourse;
  private Lesson testLesson;

  @BeforeEach
  void setUp() {
    testUser = new User();
    testUser.setUsername("testUser");
    testUser.setPassword("testPassword");
    testUser.setJoinDate(new Date());
    userService.saveUser(testUser);

    testLesson = new Lesson();
    testLesson.setTitle("Test Lesson");
    lessonService.saveLesson(testLesson);

    testCourse = new Course();
    testCourse.setName("Test Course");
    testCourse.setLastUpdated(new Date());
    testCourse.getLessons().add(testLesson);
    courseService.saveCourse(testCourse);
  }

  @AfterEach
  void tearDown() {
    userService.deleteUser(testUser);
    courseService.deleteCourse(testCourse);
    lessonService.deleteLesson(testLesson);
  }

  // Test cases for AccountController methods

  @Test
  void testGetProfile() {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> requestEntity = new HttpEntity<>(headers);

    ResponseEntity<User> response = restTemplate.exchange("/profile/" + testUser.getId(), HttpMethod.GET, requestEntity, User.class);

    System.out.println(response.getBody());
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isNotNull();
    assertThat(response.getBody().getId()).isEqualTo(testUser.getId());
  }

  @Test
  void testGetUserCourses() {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> requestEntity = new HttpEntity<>(headers);

    ResponseEntity<List> response = restTemplate.exchange(
        "/user/" + testUser.getId() + "/courses", HttpMethod.GET, requestEntity, List.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isNotNull();
  }

  // Test cases for CourseController methods

  @Test
  void testGetCourses() {
    ResponseEntity<List> response = restTemplate.getForEntity("/course", List.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isNotNull();
  }

  @Test
  void testGetParticipants() {
    ResponseEntity<List> response = restTemplate.getForEntity(
        "/course/" + testCourse.getId() + "/participants", List.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isNotNull();
  }

  // Test cases for LessonController methods

  @Test
  void testGetLesson() {
    ResponseEntity<Lesson> response = restTemplate.getForEntity("/lesson/" + testLesson.getId(),
        Lesson.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isNotNull();
    assertThat(response.getBody().getId()).isEqualTo(testLesson.getId());
  }

  @Test
  void testUpdateLesson() {
    testLesson.setTitle("Updated Test Lesson");
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<Lesson> requestEntity = new HttpEntity<>(testLesson, headers);
    ResponseEntity<Lesson> response = restTemplate.exchange("/lesson/" + testCourse.getId() + "/" + testLesson.getId(), HttpMethod.POST, requestEntity, Lesson.class);

    System.out.println("JSON Response: " + response.getBody());

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isNotNull();
    assertThat(response.getBody().getId()).isEqualTo(testLesson.getId());
    assertThat(response.getBody().getTitle()).isEqualTo("Updated Test Lesson");
  }
}

