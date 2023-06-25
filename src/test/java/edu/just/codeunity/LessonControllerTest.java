package edu.just.codeunity;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.*;
import edu.just.codeunity.controllers.*;
import edu.just.codeunity.entities.*;
import edu.just.codeunity.services.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.*;
import org.springframework.test.web.servlet.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class LessonControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private LessonService lessonService;

  @MockBean
  private CourseService courseService;

  @Test
  void whenGetLesson_thenReturnLesson() throws Exception {
    Long lessonID = 1L;
    Lesson expectedLesson = new Lesson();
    expectedLesson.setId(lessonID);
    when(lessonService.getLessonById(lessonID)).thenReturn(expectedLesson);

    mockMvc.perform(get("/lesson/{lessonID}", lessonID)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(lessonID));
  }

  @Test
  void whenUpdateLesson_thenReturnLesson() throws Exception {
    Long courseID = 1L;
    String lessonID = "1";

    Course course = new Course();
    course.setId(courseID);

    Lesson lesson = new Lesson();
    lesson.setId(Long.parseLong(lessonID));

    course.getLessons().add(lesson);
    when(courseService.getCourseById(courseID)).thenReturn(course);

    Lesson updatedLesson = new Lesson();
    updatedLesson.setId(Long.parseLong(lessonID));
    updatedLesson.setTitle("Updated Title");

    ObjectMapper objectMapper = new ObjectMapper();
    String updatedLessonJson = objectMapper.writeValueAsString(updatedLesson);

    mockMvc.perform(post("/lesson/{courseID}/{lessonID}", courseID, lessonID)
            .contentType(MediaType.APPLICATION_JSON)
            .content(updatedLessonJson))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(lessonID))
        .andExpect(jsonPath("$.title").value("Updated Title"));
  }

  @Test
  void whenUpdateLesson_withInvalidLessonID_thenReturnBadRequest() throws Exception {
    Long courseID = 1L;
    String lessonID = "999";
    Course course = new Course();
    course.setId(courseID);
    Lesson lesson = new Lesson();
    lesson.setId(1L);
    course.getLessons().add(lesson);
    when(courseService.getCourseById(courseID)).thenReturn(course);

    mockMvc.perform(post("/lesson/{courseID}/{lessonID}", courseID, lessonID)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }

  @Test
  void whenUpdateLesson_withException_thenReturnBadRequest() throws Exception {
    Long courseID = 1L;
    String lessonID = "1";
    Course course = new Course();
    course.setId(courseID);
    when(courseService.getCourseById(courseID)).thenReturn(course);

    mockMvc.perform(post("/lesson/{courseID}/{lessonID}", courseID, lessonID)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }

  @Test
  void whenPutLesson_thenReturnLesson() throws Exception {
    Long courseID = 1L;
    Lesson lesson = new Lesson();
    lesson.setId(11L);
    when(courseService.getCourseById(courseID)).thenReturn(new Course());

    mockMvc.perform(put("/lesson/{courseID}/newLesson", courseID)
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(lesson)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").isNotEmpty());
  }

  @Test
  void whenDeleteLesson_thenReturnOk() throws Exception {
    Lesson lesson = new Lesson();
    lesson.setId(1L);

    when(lessonService.getLessonById(lesson.getId())).thenReturn(lesson);

    mockMvc.perform(delete("/lesson/{lessonID}", lesson.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(lesson)))
        .andExpect(status().isOk());

    verify(lessonService, times(1)).deleteLesson(lesson);
  }

}
