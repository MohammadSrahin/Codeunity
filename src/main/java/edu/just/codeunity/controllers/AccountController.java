package edu.just.codeunity.controllers;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;
import edu.just.codeunity.entities.*;
import edu.just.codeunity.services.*;
import java.util.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {
  private final ObjectMapper objectMapper;

  private final UserService userService;
  private final CourseService courseService;

  public AccountController(ObjectMapper objectMapper, UserService userService, CourseService courseService) {
    this.objectMapper = objectMapper;
    this.userService = userService;
    this.courseService = courseService;
  }

  @GetMapping(value = "/profile/{userID}", consumes = "application/json")
  public User getProfile(@PathVariable Long userID) {
    return userService.getUserById(userID);
  }

  @GetMapping(value = "/user/{userID}/courses", consumes = "application/json")
  public List<Course> getUserCourses(@PathVariable Long userID) {
    User user = userService.getUserById(userID);
    return courseService.getAllCoursesUserEnrolledIn(user);
  }

  @PostMapping( value = "/profile/{userID}", consumes = "application/json")
  public User updateProfile(@PathVariable Long userID, @RequestBody User updatedUser) {
    User user = userService.getUserById(userID);

    user.updateUser(updatedUser);
    return updatedUser;
  }

  @PostMapping(value = "/login", consumes = "application/json")
  public User login(@RequestBody User user) {
    return userService.getUserByUsernameAndPassword(user.getUsername(), user.getPassword());
  }

  @PostMapping(value = "/register", consumes = "application/json")
  public HttpStatus register(@RequestBody User user) {
    if (user == null) {
      return HttpStatus.NOT_ACCEPTABLE;
    }
    user.setJoinDate(new Date());

    userService.saveUser(user);
    System.out.println(user);
    return HttpStatus.OK;
  }
}
