package edu.just.codeunity.controllers;

import com.fasterxml.jackson.databind.node.*;
import edu.just.codeunity.entities.*;
import edu.just.codeunity.services.*;
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
  public Lesson updateLesson(@PathVariable Long courseID, @PathVariable String lessonID) {
    Course course = courseService.getCourseById(courseID);
    Lesson lesson = course.getLessons().stream()
        .filter(l -> l.getId().equals(lessonID))
        .findFirst().orElse(null);

    return lesson;
  }


  @PutMapping("/{courseID}/newLesson")
  public Lesson putLesson(@PathVariable Long courseID, @RequestBody Lesson lesson) {
    Course course = courseService.getCourseById(courseID);
    lessonService.saveLesson(lesson);
    course.getLessons().add(lesson);

    return lesson;
  }

  @DeleteMapping("/{lessonID}")
  public ObjectNode deleteLesson(@PathVariable String lessonID) {
    ObjectNode node = objectMapper.createObjectNode();

    return node;
  }

}
