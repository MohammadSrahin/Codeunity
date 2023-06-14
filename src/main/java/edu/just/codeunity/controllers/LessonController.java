package edu.just.codeunity.controllers;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;
import edu.just.codeunity.entities.*;
import edu.just.codeunity.services.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lesson")
public class LessonController {

  private final LessonService lessonService;
  private final CourseService courseService;
  public LessonController(LessonService lessonService, CourseService courseService) {
    this.lessonService = lessonService;
    this.courseService = courseService;
  }
  @GetMapping("/{lessonID}")
  public Lesson getLesson(@PathVariable Long lessonID) {
    return lessonService.getLessonById(lessonID);
  }

  @PostMapping("/{courseID}/{lessonID}")
  public ResponseEntity<Lesson> updateLesson(@PathVariable Long courseID, @PathVariable String lessonID) {
    Course course = courseService.getCourseById(courseID);

    try {
      Lesson lesson = course.getLessons().stream()
          .filter(l -> l.getId() == Long.parseLong(lessonID))
          .findFirst().orElse(null);
      if (lesson == null) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }

      return new ResponseEntity<>(lesson, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }


  @PutMapping("/{courseID}/newLesson")
  public Lesson putLesson(@PathVariable Long courseID, @RequestBody Lesson lesson) {
    Course course = courseService.getCourseById(courseID);
    lessonService.saveLesson(lesson);
    course.getLessons().add(lesson);
    courseService.saveCourse(course);

    return lesson;
  }

  @DeleteMapping("/{lessonID}")
  public ObjectNode deleteLesson(@PathVariable String lessonID) {
    ObjectNode node = new ObjectMapper().createObjectNode();

    return node;
  }

}
