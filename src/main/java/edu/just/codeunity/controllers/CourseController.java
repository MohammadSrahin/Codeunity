package edu.just.codeunity.controllers;

import edu.just.codeunity.entities.*;
import edu.just.codeunity.services.*;
import java.util.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
public class CourseController {

  private final UserService userService;
  private final CourseService courseService;

  public CourseController(UserService userService, CourseService courseService) {
    this.userService = userService;
    this.courseService = courseService;
  }

  @GetMapping
  public List<Course> getCourses() {
    return courseService.getAllCourses();
  }

  @GetMapping("/{courseID}/participants")
  public List<User> getParticipants(@PathVariable Long courseID) {
    return userService.getAllUsersEnrolledInCertainCourse(courseID);
  }

  @GetMapping("/{courseID}")
  public Course getCourse(@PathVariable Long courseID) {
    return courseService.getCourseById(courseID);
  }

  @PostMapping("/{courseID}")
  public Course updateCourse(@PathVariable Long courseID, @RequestBody Course updatedCourse) {
    Course course = courseService.getCourseById(courseID);

    course.updateCourse(course);

    return course;
  }

  @PostMapping("/course/new")
  public Course newCourse(@RequestBody Course course) {
    course.setLastUpdated(new Date());
    courseService.saveCourse(course);
    return course;
  }


  @DeleteMapping("/{courseID}")
  public HttpStatus deleteCourse(@PathVariable long courseID) {
    Course course = courseService.getCourseById(courseID);
    courseService.deleteCourse(course);
    return HttpStatus.OK;
  }
}
