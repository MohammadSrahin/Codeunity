package edu.just.codeunity;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.*;
import edu.just.codeunity.controllers.*;
import edu.just.codeunity.entities.*;
import edu.just.codeunity.services.*;
import java.util.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.*;
import org.springframework.test.web.servlet.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CourseController.class)
@AutoConfigureMockMvc(addFilters = false)
class CourseControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserService userService;

  @MockBean
  private CourseService courseService;

  @Test
  void whenGetCourses_thenReturnCourseList() throws Exception {
    Course course1 = new Course();
    Course course2 = new Course();
    List<Course> courseList = Arrays.asList(course1, course2);

    when(courseService.getAllCourses()).thenReturn(courseList);

    mockMvc.perform(get("/course")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$[0].id").value(course1.getId()))
        .andExpect(jsonPath("$[1].id").value(course2.getId()));
  }

  @Test
  void whenGetParticipants_thenReturnUserList() throws Exception {
    Long courseID = 123L;
    User user1 = new User();
    User user2 = new User();
    List<User> userList = Arrays.asList(user1, user2);

    when(userService.getAllUsersEnrolledInCertainCourse(courseID)).thenReturn(userList);

    mockMvc.perform(get("/course/{courseID}/participants", courseID)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$[0].id").value(user1.getId()))
        .andExpect(jsonPath("$[1].id").value(user2.getId()));
  }

  @Test
  void whenGetCourse_thenReturnCourse() throws Exception {
    Long courseID = 123L;
    Course course = new Course();

    when(courseService.getCourseById(courseID)).thenReturn(course);

    mockMvc.perform(get("/course/{courseID}", courseID)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(course.getId()));
  }

  @Test
  void whenUpdateCourse_thenReturnUpdatedCourse() throws Exception {
    Long courseID = 123L;
    Course course = new Course();
    course.setId(courseID);

    Course updatedCourse = new Course();
    updatedCourse.setId(courseID);
    updatedCourse.setName("Updated Course");

    when(courseService.getCourseById(courseID)).thenReturn(course);
    when(courseService.saveCourse(course)).thenReturn(updatedCourse);

    mockMvc.perform(post("/course/{courseID}", courseID)
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(updatedCourse)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(courseID))
        .andExpect(jsonPath("$.name").value("Updated Course"));
  }

  @Test
  void whenNewCourse_thenReturnSavedCourse() throws Exception {
    Course course = new Course();

    when(courseService.saveCourse(course)).thenReturn(course);

    mockMvc.perform(post("/course/new")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(course)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(course.getId()))
        .andExpect(jsonPath("$.lastUpdated").isNotEmpty());
  }

  @Test
  void whenDeleteCourse_thenReturnOkStatus() throws Exception {
    Long courseID = 123L;
    Course course = new Course();

    when(courseService.getCourseById(courseID)).thenReturn(course);

    mockMvc.perform(delete("/course/{courseID}", courseID)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }
}
