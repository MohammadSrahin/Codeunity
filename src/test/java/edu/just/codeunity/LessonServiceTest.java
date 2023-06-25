package edu.just.codeunity;

import edu.just.codeunity.entities.Lesson;
import edu.just.codeunity.services.LessonService;
import java.util.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.*;

@SpringBootTest
class LessonServiceTest {

  @Autowired
  private LessonService lessonService;

  private Lesson lesson;

  @BeforeEach
  public void setup() {
    lesson = new Lesson();
    lesson.setTitle("Test Lesson");
  }

  @Test
  void testSaveLesson() {
    Lesson savedLesson = lessonService.saveLesson(lesson);
    Assertions.assertNotNull(savedLesson.getId());
  }

  @Test
  void testGetLessonById() {
    Lesson savedLesson = lessonService.saveLesson(lesson);
    Long lessonId = savedLesson.getId();

    Lesson retrievedLesson = lessonService.getLessonById(lessonId);
    Assertions.assertEquals(lesson.getTitle(), retrievedLesson.getTitle());
  }

  @Test
  void testDeleteLesson() {
    Lesson savedLesson = lessonService.saveLesson(lesson);
    Long lessonId = savedLesson.getId();

    lessonService.deleteLesson(savedLesson);

    Assertions.assertThrows(NoSuchElementException.class, () -> {
      lessonService.getLessonById(lessonId);
    });
  }
}
