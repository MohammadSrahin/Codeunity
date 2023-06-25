package edu.just.codeunity.controllers;

import edu.just.codeunity.entities.*;
import edu.just.codeunity.services.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lesson")
public class LessonController {

  private final LessonService lessonService;
  private final CourseService courseService;
  private final ExamService examService;
  public LessonController(LessonService lessonService, CourseService courseService,
      ExamService examService) {
    this.lessonService = lessonService;
    this.courseService = courseService;
    this.examService = examService;
  }
  @GetMapping("/{lessonID}")
  public Lesson getLesson(@PathVariable Long lessonID) {
    return lessonService.getLessonById(lessonID);
  }

  @PostMapping("/{courseID}/{lessonID}")
  public ResponseEntity<Lesson> updateLesson(@PathVariable Long courseID, @PathVariable Long lessonID, @RequestBody Lesson updatedLesson) {
    Course course = courseService.getCourseById(courseID);

    try {
      Lesson lesson = course.getLessons().stream()
          .filter(l -> l.getId().equals(lessonID))
          .findFirst().orElse(null);

      if (lesson == null) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }

      lesson.updateLesson(updatedLesson);
      if (lesson.getExam() != null) {
        examService.saveExam(lesson.getExam());
      }

      lessonService.saveLesson(lesson);

      return new ResponseEntity<>(lesson, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }


  @PutMapping("/{courseID}/newLesson")
  public Lesson putLesson(@PathVariable Long courseID, @RequestBody Lesson lesson) {
    Course course = courseService.getCourseById(courseID);
    if (lesson.getExam() != null) {
      examService.saveExam(lesson.getExam());
    }
    lessonService.saveLesson(lesson);

    course.getLessons().add(lesson);
    courseService.saveCourse(course);

    return lesson;
  }

  @DeleteMapping("/{lessonID}")
  public ResponseEntity<HttpStatus> deleteLesson(@PathVariable Long lessonID) {
    Lesson lesson = lessonService.getLessonById(lessonID);

    if (lesson == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    lessonService.deleteLesson(lesson);

    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/{lessonID}/exam")
  public ResponseEntity<Exam> getExam(@PathVariable Long lessonID) {
    Lesson lesson = lessonService.getLessonById(lessonID);

    if (lesson == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(lesson.getExam(), HttpStatus.OK);
  }

  @PostMapping("/{lessonID}/exam")
  public ResponseEntity<Exam> addExam(@PathVariable Long lessonID, @RequestBody Exam exam) {
    Lesson lesson = lessonService.getLessonById(lessonID);

    if (lesson == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    lesson.setExam(exam);

    lessonService.saveLesson(lesson);

    return new ResponseEntity<>(lesson.getExam(), HttpStatus.OK);
  }
}
